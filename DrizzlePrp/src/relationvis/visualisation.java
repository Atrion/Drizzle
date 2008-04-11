/*
    Drizzle - A general Myst tool.
    Copyright (C) 2008  Dustin Bernard.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/ 

package relationvis;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;
import java.util.Random;
import uru.m;

/**
 *
 * @author user
 */
public class visualisation
{
    Vector<entity> entities;
    Vector<relation> relations;
    BufferedImage rawimg;
    Graphics2D img;
    boolean movemode = false;
    entity moveentity;
    Graphics output;

    int width = 950;
    int height = 550;
    public int leftborder = 200; //the white space to not fill up randomly. Must be less than width.
    boolean includeRelationsThatPointToThemselves = false;
    public int boxsize = 4; //the actual box will be this + 1 pixels wide.
    Color marked = Color.RED;
    Color unmarked = Color.BLUE;
    Color background = Color.WHITE;
    Color arrow = Color.GREEN;
    
    public void deleteMarkedEntities()
    {
        //make list of entities to remove.
        int numents = entities.size();
        Vector<entity> removees = new Vector<entity>();
        for(int i=0;i<numents;i++)
        {
            if(entities.get(i).ismarked)
            {
                removees.add(entities.get(i));
            }
        }

        //actually remove them.
        for(int i=0;i<removees.size();i++)
        {
            entities.remove(removees.get(i));
        }
        
        //remove dead relations.
        int relcount = relations.size();
        Vector<relation> deletees2 = new Vector<relation>();
        for(int i=0;i<relcount;i++)
        {
            relation currel = relations.get(i);
            if(!hasEntity(currel.from.name) || !hasEntity(currel.to.name))
            {
                deletees2.add(currel);
            }
        }
        //actually remove them.
        for(int i=0;i<deletees2.size();i++)
        {
            relations.remove(deletees2.get(i));
        }
    }
    
    public void markLinks(boolean doLeftLinks, boolean doRightLinks)
    {
        int numents = entities.size();
        for(int i=0;i<numents;i++) //set all the temps to false.
        {
            entity curent = entities.get(i);
            curent.temp1 = false;
        }
        for(int i=0;i<numents;i++) //set all the temps that are leftlinks.
        {
            entity curent = entities.get(i);
            if(curent.ismarked)
            {
                curent.temp1 = true;
                if(doLeftLinks)
                {
                    for(int j=0;j<curent.leftlinks.size();j++)
                    {
                        curent.leftlinks.get(j).temp1 = true; //mark links.
                    }
                }
                if(doRightLinks)
                {
                    for(int j=0;j<curent.rightlinks.size();j++)
                    {
                        curent.rightlinks.get(j).temp1 = true; //mark links.
                    }
                }
            }
        }
        for(int i=0;i<numents;i++) //make temp1 the actual ismarked.
        {
            entity curent = entities.get(i);
            curent.ismarked = curent.temp1;
        }
    }
    
    public void markEntitiesThatStartWith(String start)
    {
        int numents = entities.size();
        start = start.toLowerCase();
        for(int i=0;i<numents;i++)
        {
            if (entities.get(i).name.toLowerCase().startsWith(start))
            {
                entities.get(i).ismarked = true;
            }
        }
    }
    public void markEntitiesThatEndWith(String end)
    {
        int numents = entities.size();
        end = end.toLowerCase();
        for(int i=0;i<numents;i++)
        {
            if (entities.get(i).name.toLowerCase().endsWith(end))
            {
                entities.get(i).ismarked = true;
            }
        }
    }
    
    public void clearAllMarks()
    {
        int numents = entities.size();
        for(int i=0;i<numents;i++)
        {
            entities.get(i).ismarked = false;
        }
    }
    
    public void handleClick(int downOrUp, int mousebutton, int xpos, int ypos)
    {
        //mousebuttons: 1=left, 2=middle, 3=right, 0=move(my own special value)
        if(downOrUp==0) //down
        {
            if(mousebutton==1) //left-click
            {
                /*if(movemode)
                {
                    m.msg("leaving move mode.");
                    movemode = false;
                    moveentity.xpos = xpos;
                    moveentity.ypos = ypos;
                    drawAll();
                }
                else
                { */ 
                entity ent = findEntityAtPixel(xpos,ypos);
                if(ent!=null)
                {
                    //m.msg("entering move mode.");
                    movemode = true;
                    moveentity = ent;
                }
                //else m.msg("no entity there.");
                //}
            }
            else if(mousebutton==3) //middle-click
            {
                entity ent = findEntityAtPixel(xpos,ypos);
                if(ent!=null)
                {
                    //m.msg("marking.");
                    ent.ismarked = !ent.ismarked;
                    this.drawAll();
                }
                //else m.msg("no entity there.");
            }
            else if(mousebutton==2||mousebutton==0) //right-click
            {
                entity ent = findEntityAtPixel(xpos,ypos);
                if(ent!=null)
                {
                    //m.msg("getting info.");
                    drawMessage("Name:"+ent.name);
                    //m.msg("Name:"+ent.name);
                }
                //else m.msg("no entity there(right).");
            }
        }
        else //up
        {
            if(mousebutton==1) //left-click
            {
                if(movemode)
                {
                    //m.msg("leaving move mode.");
                    movemode = false;
                    moveentity.xpos = xpos;
                    moveentity.ypos = ypos;
                    drawAll();
                }
            }
        }
    }
    
    public void drawMessage(String s)
    {
        //img.drawString(s, 10, 10);
        m.msg(s); //TODO: change this to actually draw it on the image.
    }
    
    public void drawAll()
    {
        img.setColor(background);
        img.fillRect(0, 0, width, height);
        //img.setColor(Color.BLUE);
        this.drawRelations();
        this.drawEntities();
        //this.drawToCanvas(output);
        this.repaint();
    }
    
    public entity findEntityAtPixel(int xpos, int ypos)
    {
        int numents = entities.size();
        //for(int i=0;i<numents;i++)
        for(int i=numents-1;i>=0;i--) //go backwards, so the one that appears on top will be selected first.
        {
            entity curent = entities.get(i);
            if( xpos>=curent.xpos && xpos<=curent.xpos+boxsize && ypos>=curent.ypos && ypos<=curent.ypos+boxsize )
            {
                return curent;
            }
        }
        return null;
    }
    
    public void assignRandomPositions()
    {
        Random rng = new Random();
        int numents = entities.size();
        for(int i=0;i<numents;i++)
        {
            int xpos = leftborder + rng.nextInt((width - leftborder) - boxsize);
            int ypos = rng.nextInt(height-boxsize);
            entity curent = entities.get(i);
            curent.xpos = xpos;
            curent.ypos = ypos;
        }
    }
    public void drawRelations()
    {
        img.setColor(arrow);
        int numrels = relations.size();
        for(int i=0;i<numrels;i++)
        {
            relation currel = relations.get(i);
            entity from = currel.from;
            entity to = currel.to;
            img.drawLine(from.xpos, from.ypos, to.xpos, to.ypos);
            if(from.ismarked || to.ismarked) //draw arrows if marked.
            {
                double k = 0.90; double l = 0.05;
                int x0 = (int)(from.xpos + k*(to.xpos-from.xpos) - l*(to.ypos-from.ypos));
                int x1 = (int)(from.xpos + k*(to.xpos-from.xpos) + l*(to.ypos-from.ypos));
                int y0 = (int)(from.ypos + k*(to.ypos-from.ypos) + l*(to.xpos-from.xpos));
                int y1 = (int)(from.ypos + k*(to.ypos-from.ypos) - l*(to.xpos-from.xpos));
                //int num1 = 9; int den1 = 10; int num2 = 1; int den2 = 20; //just like the floats above.
                //int x0 = from.xpos + (to.xpos-from.xpos)*num1/den1 - (to.ypos-from.ypos)*num2/den2;
                //int x1 = from.xpos + (to.xpos-from.xpos)*num1/den1 + (to.ypos-from.ypos)*num2/den2;
                //int y0 = from.ypos + (to.ypos-from.ypos)*num1/den1 + (to.xpos-from.xpos)*num2/den2;
                //int y1 = from.ypos + (to.ypos-from.ypos)*num1/den1 - (to.xpos-from.xpos)*num2/den2;
                img.drawLine(x0, y0, to.xpos, to.ypos);
                img.drawLine(x1, y1, to.xpos, to.ypos);
            }
        }
    }
    public void drawEntities()
    {
        int numents = entities.size();
        for(int i=0;i<numents;i++)
        {
            entity curent = entities.get(i);
            if(curent.ismarked)
            {
                img.setColor(marked);
                img.drawRect(curent.xpos, curent.ypos, boxsize, boxsize);
                img.fillRect(curent.xpos, curent.ypos, boxsize, boxsize); //make it nice and visible.
            }
            else
            {
                img.setColor(unmarked);
                img.drawRect(curent.xpos, curent.ypos, boxsize, boxsize);
            }
            
        }
    }
    
    public void addEntity(String entityname)
    {
        if(!hasEntity(entityname))
        {
            addEntity(new entity(entityname));
        }
    }
    public void addRelation(String fromname, String toname)
    {
        entity from = findEntity(fromname);
        entity to = findEntity(toname);
        if(from!=null && to!=null)
        {
            if(includeRelationsThatPointToThemselves || !from.equals(to)) //don't include relations that point to themselves.
            {
                if(!hasRelation(fromname,toname)) //if we don't already have it...
                {
                    addRelation(new relation(from,to));
                    from.rightlinks.add(to); //add to link list.
                    to.leftlinks.add(from); //add to link list.
                }
            }
        }
        else
        {
            m.err("not both entities were found.");
        }
    }
    public boolean hasEntity(String name)
    {
        return (findEntity(name)!=null);
    }
    public boolean hasRelation(String fromname, String toname)
    {
        return (findRelation(fromname,toname)!=null);
    }
    public entity findEntity(String name)
    {
        int numentities = entities.size();
        for(int i=0;i<numentities;i++)
        {
            if(entities.get(i).name.equals(name))
            {
                return entities.get(i);
            }
        }
        return null;
    }
    public relation findRelation(String fromname, String toname)
    {
        int size = relations.size();
        for(int i=0;i<size;i++)
        {
            if(relations.get(i).from.name.equals(fromname) && relations.get(i).to.name.equals(toname))
            {
                return relations.get(i);
            }
        }
        return null;
    }
    public void addEntity(entity ent)
    {
        entities.add(ent);
    }
    public void addRelation(relation rel)
    {
        relations.add(rel);
    }
    /*public visualisation(Vector<entity> entities2)
    {
        entity[] entities3 = new entity[0];
        entities3 = entities2.toArray(entities3);
        initialisation(entities3);
    }*/
    public visualisation(Graphics output2, int width2, int height2)//entity[] entities2)
    {
        width = width2;
        height = height2;
        output = output2;
        //initialisation(entities2);
        entities = new Vector<entity>();
        relations = new Vector<relation>();
        rawimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        img = rawimg.createGraphics();
        //img.setColor(Color.BLUE);
        //img.drawLine(0, 0, 100, 100);
    }
    /*private void initialisation()//entity[] entities2)
    {
        //entities = entities2;
        rawimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        img = rawimg.createGraphics();
        img.setColor(Color.BLUE);
        img.drawLine(0, 0, 100, 100);
    }*/
    
    public void drawToCanvas(Graphics dest)
    {
        drawToCanvas(dest,0,0);
    }
    public void drawToCanvas(Graphics dest, int xpos, int ypos)
    {
        dest.drawImage(rawimg, xpos, ypos, null);
    }
    public void repaint()
    {
        this.drawToCanvas(output);
    }
}
