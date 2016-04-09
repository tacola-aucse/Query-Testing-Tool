/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package querytesting_tool;

/**
 *
 * @author root
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.Savepoint;
import java.util.*;
public class Query_Testing_Tool extends JFrame {
    JTextField   m_fileNameTF  = new JTextField(15);
    JFileChooser m_fileChooser = new JFileChooser();
    JTextField save_filename = new JTextField(20);
     File file1,file;
    
    Query_Testing_Tool()
    {
        m_fileNameTF.setEditable(false);
        save_filename.setEditable(false);
        JButton openButton = new JButton("Open");
        JButton saveButton =new JButton("Save");
        JButton convertButton = new JButton("Query Testing");
        
         openButton.addActionListener(new Query_Testing_Tool.OpenAction());
        saveButton.addActionListener(new Query_Testing_Tool.SaveAction());
        convertButton.addActionListener(new Query_Testing_Tool.convertAction());
        
         JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        

        content.add(new JLabel("Choosed File"));
        
        content.add(m_fileNameTF);
         content.add(openButton);
         
        content.add(new JLabel("Saved Dir"));
        content.add(save_filename);
            content.add(saveButton);
            content.add(convertButton) ;
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(content);
        this.pack();
    }
    
        ///////////////////////////////////////////////////// OpenAction
    class OpenAction implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            //... Open a file dialog.
            int retval = m_fileChooser.showOpenDialog(Query_Testing_Tool.this);
            if (retval == JFileChooser.APPROVE_OPTION) {
                //... The user selected a file, process it.
                file = m_fileChooser.getSelectedFile();
                System.out.println(file.getAbsolutePath());
                //... Update user interface.
                m_fileNameTF.setText(file.getName());

            // fileinput =(String) m_fileNameTF.getText();
            }
        }
    }
////////////////////////////////////////////////////////saveAction
    class SaveAction implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
         //... Save a file dialog.
        int retval = m_fileChooser.showSaveDialog(Query_Testing_Tool.this);
         //... The user selected a file, process it.
         file1 = m_fileChooser.getSelectedFile();
        //... Update user interface.
         save_filename.setText(file1.getName());
       //   fileoutput = (String)save_filename.getText();
       System.out.println(file1.getAbsolutePath());  
    }
    }
    
     class convertAction implements ActionListener{
         public void actionPerformed(ActionEvent ae){
         
             try {
            String temp, temp1, s_check, s_temp1, s_temp2,s_temp3,s_temp4,s_temp5;
            String c_check, c_temp1, c_temp2,c_temp3,c_temp4,c_temp5,c_temp6;
            int count = 0;
             BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            BufferedWriter op1 = new BufferedWriter(new FileWriter(new File(file1.getAbsolutePath()+".txt"), true));
         //   BufferedReader br = new BufferedReader(new FileReader("/root/Desktop/giveninput.txt"));
          //  BufferedWriter op1 = new BufferedWriter(new FileWriter(new File("/root/Desktop/givenout.txt"), true));
            while ((temp = br.readLine()) != null) {
                boolean flag = false;
                s_check = temp.trim();
                String spl_wd1[] = s_check.split("#");
                s_temp1 = spl_wd1[0];
                s_temp2 = spl_wd1[1];
                s_temp3 = spl_wd1[2];
                s_temp4 = spl_wd1[3];
                s_temp5 = spl_wd1[4];
                
                System.out.println(s_check + "+++++++++++");

                BufferedReader br1 = new BufferedReader(new FileReader("./already.txt"));
                while ((temp1 = br1.readLine()) != null) {

                    c_check = temp1.trim();
                    String cpl_wd[] = c_check.split("#");
                    c_temp1 = cpl_wd[0];
                    c_temp2 = cpl_wd[1];
                    c_temp3 = cpl_wd[2];
                    c_temp4 = cpl_wd[3];
                    c_temp5 = cpl_wd[4];
                    c_temp6 = cpl_wd[5];
                  //  System.out.println(c_check+"@@@@@@@@@@@@"+"\n");
                    if (s_temp2.equals(c_temp2)) {
                     //   System.out.println(s_temp2+"@@@@@@@@");

                        if (s_temp4.equals(c_temp4)) {
                            flag = true;
                           // System.out.println("match found" + c_check);
                            System.out.println("match found" + s_check+"#"+c_temp6);
                            op1.write(c_check + "\n");
                        }
                    }

                }
                if (flag == false) {

                    System.out.println(s_check + "*************");
                    op1.write(s_check + "\n");
                }

            }
            op1.close();
        } catch (Exception e) {
        }
             
     }
     }
     public static void main(String[] args) throws IOException {
     //   //concept mor=new concept();
        
        JFrame window = new Query_Testing_Tool();
                window.setSize(380, 250);
        window.setVisible(true);
//        JOptionPane.showMessageDialog(window, "   PRECISION Value\n"+"   P@5         P@10\n"+"   0.414      0.436");
    }
}
