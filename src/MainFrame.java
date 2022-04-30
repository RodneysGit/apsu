
/**
 *  Contact: shesoyam@yahoo.com
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
 * 
 *  *********************************************************************************
 * 
 * 
 *  Projects BTC: bc1q9vwmlmf4xydussjkpx00penhkpjjqlvtz82n8w
 *  BTC will be used to sponsor future and current (ongoing) projects
 *  
 *  If you want to sponsor a specific project or would like the creation 
 *  of a project you can use the email 'shesoyam@yahoo.com' to contact.  
 */
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;

public class MainFrame extends javax.swing.JFrame implements DropTargetListener {

    List<File> files = new ArrayList<>();
    private final DefaultListModel listModel = new DefaultListModel();
    private final DropTarget dropTarget;
    String log = "Starting....\n______________\n";

    ExecCmdThread execCmdThread_;

    public MainFrame() { // main frame constructor

        initComponents();

        lstFiles.setModel(listModel);
        lstFiles.setDragEnabled(true);
        lstFiles.setTransferHandler(new FileTransferHandler());
        dropTarget = new DropTarget(lstFiles, (DropTargetListener) this);

        setLocation();

    }

    private void setLocation() {
        this.setLocationRelativeTo(null);
    }

    public static Collection<File> listFileTree(File dir) {
        Set<File> fileTree = new HashSet<>();
        if (dir == null || dir.listFiles() == null) {
            return fileTree;
        }
        for (File entry : dir.listFiles()) {
            if (entry.isFile()) {
                fileTree.add(entry);
            } else {
                fileTree.add(entry);
                fileTree.addAll(listFileTree(entry));
            }
        }
        return fileTree;
    }

    private void addFileAndFolders(File path) {
        listModel.addElement(path);
        if (path.isDirectory() == true) {
            Collection<File> listFileTree = listFileTree(path);
            for (File file_ : listFileTree) {
//                System.out.println(file_);

                if (file_.isDirectory() && rdioBtnFoldersOnly.isSelected()
                        || (file_.isFile() && rdioBtnFilesOnly.isSelected())
                        || rdioBtnAlways.isSelected()) {
                    listModel.addElement(file_);
                }
            }
        }
    }

    @Override
    public void dragEnter(DropTargetDragEvent arg0) {
        // nothing
    }

    @Override
    public void dragOver(DropTargetDragEvent arg0) {
        // nothing
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent arg0) {
        // nothing
    }

    @Override
    public void dragExit(DropTargetEvent arg0) {
        // nothing
    }

    @Override
    public void drop(DropTargetDropEvent evt) {
        int action = evt.getDropAction();
        evt.acceptDrop(action);
        try {
            Transferable data = evt.getTransferable();
            if (data.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                List<File> files_ = (List<File>) data.getTransferData(
                        DataFlavor.javaFileListFlavor);
                boolean disp = false;
                for (File file_ : files_) {
                    addFileAndFolders(file_);
                }
            }
        } catch (UnsupportedFlavorException | IOException e) {
            log = log + "\n * Error \n" + e.getMessage();

        } finally {
            evt.dropComplete(true);
        }
    }

    //https://stackoverflow.com/questions/4879956/swing-dragdrop-file-transferable
    private class FileTransferHandler extends TransferHandler {

        @Override
        protected Transferable createTransferable(JComponent c) {
            JList list = (JList) c;

            for (Object obj : list.getSelectedValues()) {
                files.add((File) obj);
            }
            return new FileTransferable(files);
        }

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;//return move= to move selected file from list to explorer
        }
    }

    private class FileTransferable implements Transferable {

        private final List<File> files;

        public FileTransferable(List<File> files) {
            this.files = files;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{DataFlavor.javaFileListFlavor};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(DataFlavor.javaFileListFlavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor)
                throws UnsupportedFlavorException, IOException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return files;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgAbout = new javax.swing.JDialog();
        pnlAbout = new javax.swing.JPanel();
        txtAreaDescrption = new javax.swing.JTextArea();
        lblDescription = new javax.swing.JLabel();
        lblAuthor = new javax.swing.JLabel();
        txtAreaAuthor = new javax.swing.JTextArea();
        lblVersion = new javax.swing.JLabel();
        lblBuildDate = new javax.swing.JLabel();
        lblJava = new javax.swing.JLabel();
        lblVersionText = new javax.swing.JLabel();
        lblBuildDateText = new javax.swing.JLabel();
        lblJavaText = new javax.swing.JLabel();
        dlgPreferences = new javax.swing.JDialog();
        pnlPreferences = new javax.swing.JPanel();
        rdioBtnAlways = new javax.swing.JRadioButton();
        rdioBtnPromptRather = new javax.swing.JRadioButton();
        rdioBtnDoNot = new javax.swing.JRadioButton();
        rdioBtnFilesOnly = new javax.swing.JRadioButton();
        rdioBtnFoldersOnly = new javax.swing.JRadioButton();
        btnGrpFilesFoldersProc = new javax.swing.ButtonGroup();
        fileChsrFolderFile = new javax.swing.JFileChooser();
        pnlMain = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        sepHeader = new javax.swing.JSeparator();
        btnExit = new javax.swing.JButton();
        btnUnhide = new javax.swing.JButton();
        btnAddFile = new javax.swing.JButton();
        btnAddFolder = new javax.swing.JButton();
        lblNotification = new javax.swing.JLabel();
        btnClearAll = new javax.swing.JButton();
        btnClearSel = new javax.swing.JButton();
        sepFooter = new javax.swing.JSeparator();
        pnlScrollPane = new javax.swing.JPanel();
        scrlpnFilesFolders = new javax.swing.JScrollPane();
        lstFiles = new javax.swing.JList();
        mnuBarMain = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuItemPref = new javax.swing.JMenuItem();
        mnuItemExit = new javax.swing.JMenuItem();
        mnuHelp = new javax.swing.JMenu();
        mnuItemAbout = new javax.swing.JMenuItem();

        dlgAbout.setBackground(new java.awt.Color(255, 255, 255));
        dlgAbout.setLocation(new java.awt.Point(0, 0));
        dlgAbout.setMinimumSize(new java.awt.Dimension(582, 291));
        dlgAbout.setResizable(false);
        dlgAbout.setSize(new java.awt.Dimension(582, 291));

        pnlAbout.setBackground(new java.awt.Color(255, 255, 255));

        txtAreaDescrption.setEditable(false);
        txtAreaDescrption.setColumns(20);
        txtAreaDescrption.setRows(5);
        txtAreaDescrption.setText("apSU - A simple utility to unhide files and folders typically\nhidden by Trojan Horse/Worm, Win32:Atraps-PZ\n\nWin32:Atraps-PZ threat typically hides a file and creates a shortcut \nmimicking the file");
        txtAreaDescrption.setAutoscrolls(false);
        txtAreaDescrption.setBorder(null);
        txtAreaDescrption.setOpaque(false);

        lblDescription.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDescription.setText("Application description:");

        lblAuthor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAuthor.setText("Author:");

        txtAreaAuthor.setColumns(20);
        txtAreaAuthor.setRows(5);
        txtAreaAuthor.setText("Rodney W.");
        txtAreaAuthor.setBorder(null);
        txtAreaAuthor.setOpaque(false);

        lblVersion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblVersion.setText("Version:");

        lblBuildDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBuildDate.setText("Build date:");

        lblJava.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblJava.setText("Java:");

        lblVersionText.setText("1.0");

        lblBuildDateText.setText("2021-04-05");

        lblJavaText.setText("1.8.0_221");

        javax.swing.GroupLayout pnlAboutLayout = new javax.swing.GroupLayout(pnlAbout);
        pnlAbout.setLayout(pnlAboutLayout);
        pnlAboutLayout.setHorizontalGroup(
            pnlAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAboutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAboutLayout.createSequentialGroup()
                        .addGroup(pnlAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBuildDate)
                            .addComponent(lblVersion)
                            .addComponent(lblJava))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJavaText)
                            .addComponent(lblVersionText)
                            .addComponent(lblBuildDateText))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAboutLayout.createSequentialGroup()
                        .addGroup(pnlAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlAboutLayout.createSequentialGroup()
                                .addComponent(lblAuthor)
                                .addGap(18, 18, 18)
                                .addComponent(txtAreaAuthor))
                            .addComponent(lblDescription, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(32, 32, 32))
                    .addGroup(pnlAboutLayout.createSequentialGroup()
                        .addComponent(txtAreaDescrption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        pnlAboutLayout.setVerticalGroup(
            pnlAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAboutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVersion)
                    .addComponent(lblVersionText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuildDate)
                    .addComponent(lblBuildDateText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJava)
                    .addComponent(lblJavaText))
                .addGap(18, 18, 18)
                .addComponent(lblDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAreaDescrption, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAuthor)
                    .addComponent(txtAreaAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        txtAreaDescrption.getAccessibleContext().setAccessibleParent(txtAreaDescrption);

        javax.swing.GroupLayout dlgAboutLayout = new javax.swing.GroupLayout(dlgAbout.getContentPane());
        dlgAbout.getContentPane().setLayout(dlgAboutLayout);
        dlgAboutLayout.setHorizontalGroup(
            dlgAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlgAboutLayout.setVerticalGroup(
            dlgAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgAboutLayout.createSequentialGroup()
                .addComponent(pnlAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        dlgPreferences.setMinimumSize(new java.awt.Dimension(400, 300));
        dlgPreferences.setResizable(false);

        pnlPreferences.setBackground(new java.awt.Color(255, 255, 255));

        btnGrpFilesFoldersProc.add(rdioBtnAlways);
        rdioBtnAlways.setSelected(true);
        rdioBtnAlways.setText("Always add sub folders and files, done recursively");
        rdioBtnAlways.setOpaque(false);

        btnGrpFilesFoldersProc.add(rdioBtnPromptRather);
        rdioBtnPromptRather.setText("Prompt me rather");
        rdioBtnPromptRather.setOpaque(false);

        btnGrpFilesFoldersProc.add(rdioBtnDoNot);
        rdioBtnDoNot.setText("Do not add sub folders and files");
        rdioBtnDoNot.setOpaque(false);

        btnGrpFilesFoldersProc.add(rdioBtnFilesOnly);
        rdioBtnFilesOnly.setText("Only add sub-files");
        rdioBtnFilesOnly.setOpaque(false);

        btnGrpFilesFoldersProc.add(rdioBtnFoldersOnly);
        rdioBtnFoldersOnly.setText("Only add sub folders(without files)");
        rdioBtnFoldersOnly.setOpaque(false);

        javax.swing.GroupLayout pnlPreferencesLayout = new javax.swing.GroupLayout(pnlPreferences);
        pnlPreferences.setLayout(pnlPreferencesLayout);
        pnlPreferencesLayout.setHorizontalGroup(
            pnlPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPreferencesLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdioBtnFoldersOnly)
                    .addComponent(rdioBtnFilesOnly)
                    .addComponent(rdioBtnDoNot)
                    .addComponent(rdioBtnPromptRather)
                    .addComponent(rdioBtnAlways))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        pnlPreferencesLayout.setVerticalGroup(
            pnlPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPreferencesLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(rdioBtnAlways)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdioBtnFilesOnly)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdioBtnFoldersOnly)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdioBtnDoNot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdioBtnPromptRather)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dlgPreferencesLayout = new javax.swing.GroupLayout(dlgPreferences.getContentPane());
        dlgPreferences.getContentPane().setLayout(dlgPreferencesLayout);
        dlgPreferencesLayout.setHorizontalGroup(
            dlgPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPreferences, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlgPreferencesLayout.setVerticalGroup(
            dlgPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPreferences, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        fileChsrFolderFile.setApproveButtonText("Select");
        fileChsrFolderFile.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        fileChsrFolderFile.setMaximumSize(new java.awt.Dimension(582, 397));
        fileChsrFolderFile.setMinimumSize(new java.awt.Dimension(582, 397));
        fileChsrFolderFile.setMultiSelectionEnabled(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        lblHeader.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lblHeader.setText("Super UNHIDE");

        btnExit.setBackground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnUnhide.setBackground(new java.awt.Color(255, 255, 255));
        btnUnhide.setText("Unhide files / folders");
        btnUnhide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnhideActionPerformed(evt);
            }
        });

        btnAddFile.setBackground(new java.awt.Color(255, 255, 255));
        btnAddFile.setText("Add file");
        btnAddFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFileActionPerformed(evt);
            }
        });

        btnAddFolder.setBackground(new java.awt.Color(255, 255, 255));
        btnAddFolder.setText("Add folder");
        btnAddFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFolderActionPerformed(evt);
            }
        });

        lblNotification.setText("Notification - drag and drop files for simplicity");

        btnClearAll.setBackground(new java.awt.Color(255, 255, 255));
        btnClearAll.setText("Clear all");
        btnClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAllActionPerformed(evt);
            }
        });

        btnClearSel.setBackground(new java.awt.Color(255, 255, 255));
        btnClearSel.setText("Clear selected");
        btnClearSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSelActionPerformed(evt);
            }
        });

        lstFiles.setBackground(new java.awt.Color(229, 242, 255));
        lstFiles.setSelectionBackground(new java.awt.Color(153, 204, 255));
        scrlpnFilesFolders.setViewportView(lstFiles);

        javax.swing.GroupLayout pnlScrollPaneLayout = new javax.swing.GroupLayout(pnlScrollPane);
        pnlScrollPane.setLayout(pnlScrollPaneLayout);
        pnlScrollPaneLayout.setHorizontalGroup(
            pnlScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlpnFilesFolders)
        );
        pnlScrollPaneLayout.setVerticalGroup(
            pnlScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlpnFilesFolders, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sepFooter)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExit))
                    .addComponent(sepHeader)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(btnUnhide, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                        .addComponent(btnClearSel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNotification)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(btnAddFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddFile, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHeader)
                    .addComponent(btnExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddFile)
                    .addComponent(btnAddFolder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUnhide)
                    .addComponent(btnClearSel)
                    .addComponent(btnClearAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepFooter, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblNotification)
                .addContainerGap())
        );

        mnuFile.setText("File");

        mnuItemPref.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        mnuItemPref.setText("Preferences");
        mnuItemPref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemPrefActionPerformed(evt);
            }
        });
        mnuFile.add(mnuItemPref);

        mnuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        mnuItemExit.setText("Exit");
        mnuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemExitActionPerformed(evt);
            }
        });
        mnuFile.add(mnuItemExit);

        mnuBarMain.add(mnuFile);

        mnuHelp.setText("Help");

        mnuItemAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        mnuItemAbout.setText("About");
        mnuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemAboutActionPerformed(evt);
            }
        });
        mnuHelp.add(mnuItemAbout);

        mnuBarMain.add(mnuHelp);

        setJMenuBar(mnuBarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnUnhideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnhideActionPerformed
        execCmdThread_ = new ExecCmdThread(this, lstFiles, lblNotification);
        execCmdThread_.start();
    }//GEN-LAST:event_btnUnhideActionPerformed

    private void btnClearSelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSelActionPerformed
        try {
            listModel.removeElementAt(lstFiles.getSelectedIndex());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not remove an item.\nPlease make sure you have selected an item.", "Error", 0);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearSelActionPerformed

    private void btnClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAllActionPerformed
        listModel.removeAllElements();       // TODO add your handling code here:
    }//GEN-LAST:event_btnClearAllActionPerformed

    private void mnuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemAboutActionPerformed
        dlgAbout.setLocationRelativeTo(null);
        dlgAbout.setVisible(true);
    }//GEN-LAST:event_mnuItemAboutActionPerformed

    private void mnuItemPrefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemPrefActionPerformed
        dlgPreferences.setLocationRelativeTo(null);
        dlgPreferences.setVisible(true);
    }//GEN-LAST:event_mnuItemPrefActionPerformed

    private void btnAddFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFileActionPerformed
        fileChsrFolderFile.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        int selection = fileChsrFolderFile.showOpenDialog(null);
        queryFolderFile(selection);
    }//GEN-LAST:event_btnAddFileActionPerformed

    private void btnAddFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFolderActionPerformed
        fileChsrFolderFile.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        int selection = fileChsrFolderFile.showOpenDialog(null);
        queryFolderFile(selection);
    }//GEN-LAST:event_btnAddFolderActionPerformed

    private void mnuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemExitActionPerformed
        if (execCmdThread_ == null) {
            System.exit(0);
        } else {
            if (execCmdThread_.isAlive()) {

            } else if (execCmdThread_.isInterrupted()) {
                System.exit(0);
            } else {
                System.exit(0);
            }
        }
    }//GEN-LAST:event_mnuItemExitActionPerformed

    private void queryFolderFile(int selection) {
        if (selection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChsrFolderFile.getSelectedFile();
            addFileAndFolders(selectedFile);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFile;
    private javax.swing.JButton btnAddFolder;
    private javax.swing.JButton btnClearAll;
    private javax.swing.JButton btnClearSel;
    private javax.swing.JButton btnExit;
    private javax.swing.ButtonGroup btnGrpFilesFoldersProc;
    private javax.swing.JButton btnUnhide;
    private javax.swing.JDialog dlgAbout;
    private javax.swing.JDialog dlgPreferences;
    private javax.swing.JFileChooser fileChsrFolderFile;
    private javax.swing.JLabel lblAuthor;
    private javax.swing.JLabel lblBuildDate;
    private javax.swing.JLabel lblBuildDateText;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblJava;
    private javax.swing.JLabel lblJavaText;
    private javax.swing.JLabel lblNotification;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JLabel lblVersionText;
    private javax.swing.JList lstFiles;
    private javax.swing.JMenuBar mnuBarMain;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JMenuItem mnuItemAbout;
    private javax.swing.JMenuItem mnuItemExit;
    private javax.swing.JMenuItem mnuItemPref;
    private javax.swing.JPanel pnlAbout;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlPreferences;
    private javax.swing.JPanel pnlScrollPane;
    private javax.swing.JRadioButton rdioBtnAlways;
    private javax.swing.JRadioButton rdioBtnDoNot;
    private javax.swing.JRadioButton rdioBtnFilesOnly;
    private javax.swing.JRadioButton rdioBtnFoldersOnly;
    private javax.swing.JRadioButton rdioBtnPromptRather;
    private javax.swing.JScrollPane scrlpnFilesFolders;
    private javax.swing.JSeparator sepFooter;
    private javax.swing.JSeparator sepHeader;
    private javax.swing.JTextArea txtAreaAuthor;
    private javax.swing.JTextArea txtAreaDescrption;
    // End of variables declaration//GEN-END:variables
}
