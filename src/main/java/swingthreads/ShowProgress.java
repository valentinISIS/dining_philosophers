package swingthreads;

import javax.swing.SwingUtilities;

public class ShowProgress extends javax.swing.JFrame {

    class MyThread extends Thread {

        int i;

        @Override
        public void run() {
            for (i = 0; i < 100; i += 5) {
                try {
                    sleep(1000); // Faire une étape du traitement
                    // Interdit : Swing n'est pas Thread Safe
                    //jProgressBar1.setValue(i); // Montrer l'avancement dans le progressBar
                    // Syntaxe "inner class"
                    SwingUtilities.invokeLater(
                            new Runnable() {
                                @Override
                                public void run() {
                                    jProgressBar1.setValue(i);
                                }
                            }
                    );
                } catch (InterruptedException ex) {
                }
            }
            // On réactive le bouton
            // Syntaxe "lambda expression"
            SwingUtilities.invokeLater(
                    () -> { jButton1.setEnabled(true); }
            );
        }
    }

    /**
     * Creates new form ShowProgress
     */
    public ShowProgress() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Threads and Swing");
        getContentPane().add(jProgressBar1, java.awt.BorderLayout.PAGE_END);

        jButton1.setText("Start Thread");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    new MyThread().start();
    jButton1.setEnabled(false);
}//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new ShowProgress().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
