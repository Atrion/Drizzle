/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * dvGUI.java
 *
 * Created on 3-Nov-2009, 11:50:43 AM
 */

package deepview2;

import shared.m;

public class dvGUI extends javax.swing.JFrame {

    /** Creates new form dvGUI */
    dvAges ages;
    javax.swing.JTree tree;
    javax.swing.JDesktopPane desktop;

    public dvGUI() {
        //this.setUndecorated(true);
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.tree = this.jTree1;
        this.desktop = this.jDesktopPane1;
        this.ages = new dvAges(this);
        //this.tree.setMinimumSize(new java.awt.Dimension(100, 0));
        //this.tree.setPreferredSize(new java.awt.Dimension(200, 0));
        this.jSplitPane1.setDividerLocation(300);
        //this.setPreferredSize(new java.awt.Dimension(1024, 768));
        this.setSize(1024, 768);
        //this.jSplitPane1.setPreferredSize(new java.awt.Dimension(1024, 768));
        //this.setMinimumSize(new java.awt.Dimension(1024, 768));
        this.setTitle(shared.translation.translate("Drizzle Deepview"));
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        jButton1.setText("Open...");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setText("Save All...");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setText("Reload...");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jScrollPane1.setViewportView(jTree1);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jScrollPane2.setViewportView(jDesktopPane1);

        jSplitPane1.setRightComponent(jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String key = "dvLastFile";
        String lastfile = shared.State.AllStates.getStateAsString(key);
        String newfile = shared.GuiUtils.getUserSelectedFile(lastfile);
        if(newfile!=null)
        {
            shared.State.AllStates.setState(key,newfile);
            ages.loadPrp(newfile);
            //refreshTree();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Save all...
        try{
            ages.onSave();
        }catch(Exception e){
            m.err("Unable to save due to error.");
            e.printStackTrace();
            shared.GuiUtils.DisplayMessage("Save Error", "Unable to save all prps.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clearWindows();
        refreshTree();
    }//GEN-LAST:event_jButton3ActionPerformed
    void clearWindows()
    {
        for(javax.swing.JInternalFrame frame: desktop.getAllFrames())
        {
            frame.doDefaultCloseAction();
        }
    }
    void refreshTree()
    {
        //tree.get
        ages.refreshTree();
        //tree.revalidate();
        //tree.repaint();
        //this.validate();
        //this.repaint();

    }
    /**
    * @param args the command line arguments
    */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dvGUI().setVisible(true);
            }
        });
    }*/

    public static void open()
    {
        open(null);
    }
    public static void open(final String filename)
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dvGUI dvgui = new dvGUI();
                dvgui.setVisible(true);
                //java.awt.GraphicsDevice gd = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                //gd.setFullScreenWindow(dvgui);
                if(filename!=null && !filename.equals(""))
                {
                    dvgui.ages.loadPrp(filename);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

}
