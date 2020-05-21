package pack1;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * author Bousri Houssam
 */

public class P extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public P() {
		// TODO Auto-generated constructor stub
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
						
			//
			//trigger exception
			//int kk = 14/0;
			//		
			
			JFileChooser fi = new JFileChooser();
		    fi.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		    fi.showOpenDialog(null);
		    String path = fi.getSelectedFile().toString();
		    

			File file = new File(path);
			String project = file.getName();
			File packs = new File(path+"/src/");
			
			//on recupere tts les packages du projet
			String[] directories = packs.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
			});
			int nbPacks = directories.length;
			//System.out.println(Arrays.toString(directories));
			
			
			//pour recuperer le nombre total des classes dans tout les packages
			List<String> lc = new ArrayList<String>();
			for (int i = 0; i < nbPacks; i++) {
				File cls = new File(packs+"/"+directories[i]+"/");
				String[] classes = cls.list(new FilenameFilter() {
					  @Override
					  public boolean accept(File current, String name) {
					    return new File(current, name).isFile();
					  }
					});
				for (int j = 0; j < classes.length; j++) {
					lc.add(classes[j]);
				}
			}	

			/*
			
			*/
			
			for (int j = 0; j < nbPacks; j++) {
				
				List<String> lcc = new ArrayList<String>();
			
					File cls = new File(packs+"/"+directories[j]+"/");
					String[] classes = cls.list(new FilenameFilter() {
						  @Override
						  public boolean accept(File current, String name) {
						    return new File(current, name).isFile();
						  }
						});
					for (int jj = 0; jj < classes.length; jj++) {
						lcc.add(classes[jj]);
					}
					
					for (int i = 0; i <lcc.size() ; i++) {
						
					}
			}
			
			Generer gen = new Generer(directories, lc, path);
		 
			JFrame frame = new JFrame("Description graphique de: "+ project);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
			frame.setSize(660, 800);
			frame.add(gen);
			frame.setVisible(true);
		    
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.toString());
			ExceptionSave s = new ExceptionSave();
			s.save(e);
		}
	}
}
