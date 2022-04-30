/**
 *  Projection creation date:  possibly 2013-2015
 *  Project revision date: 2022-04-29
 *  Project name: apSU // short for "Application SUPER UNHIDE"
 *  
 *  // this project's name may change at any moment
 * 
 *  Objection:
 *  Project was created as a result of constant infection from
 *  Win32:Atraps-PZ[Trojan] which would hide folders and files.
 *  During the time Avast Anti-virus was used to quarantine the said Trojan however 
 *  Avast was not correcting the folder structure to make hidden files and folders visible
 *  - hence the creation of this Project.
 * 
 *  A program called "HFV (Hidden Folder Virus) Cleaner Pro"
 *  can achieve the same folder/file structure correction as well as able to neutralize
 *  the above said Virus
 * 
 *  URL: https://sourceforge.net/projects/hfv/
 */

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

public class ExecCmdThread extends Thread {
    private final DefaultListModel lstFilesModel;
    JLabel lblNotification;
    JList lstFiles;

    ExecCmdThread(MainFrame aThis, JList lstFiles_, JLabel lnlNotification_) {
        lstFiles = lstFiles_;
        lblNotification = lnlNotification_;
        lstFilesModel =  (DefaultListModel) lstFiles.getModel();
    }
    
    private void exeCommand(String command){
//        System.out.println(command);
        Process process_;
        try {
                process_ = Runtime.getRuntime().exec(command);
                process_.waitFor();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        Object[] toArray = lstFilesModel.toArray();
        String os = System.getProperty("os.name");
        String workingFile = "";
        for (Object filess_ : toArray) {
            try {
                File file = new File(filess_.toString());
                String fileAbsPath = file.getAbsolutePath();
                String fileName = file.getName();

                String command = "";
                if(os.startsWith("Windows")){
                    command= "cmd /c attrib -s -h \"" + fileAbsPath + "\"";
                }
                
                exeCommand(command);

                int wrkFileStrLen = fileName.length();
                if (wrkFileStrLen > 40) {
                    workingFile = fileName.substring(0, 35) + "..." 
                            + fileName.substring(wrkFileStrLen-9, wrkFileStrLen)
                            + " unhidden";
                } else {
                    workingFile = fileName + " unhidden";
                }

            } finally {
                    lblNotification.setText(workingFile);
            }
        }
        lstFilesModel.removeAllElements();
        lblNotification.setText("- Hidded files and folders are now unhidden - ");
    }
    
    
}
