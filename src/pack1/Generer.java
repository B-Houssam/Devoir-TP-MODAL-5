package pack1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;

import javax.swing.JPanel;

public class Generer extends JPanel{
	
	/**
	 * author Houssam Bousri
	 */
	private static final long serialVersionUID = 1L;
	
	List<String> classes = new ArrayList<String>();
	String[] packages;
	String path;
	
	public Generer(String[] packs, List<String> lc, String path) {
		// TODO Auto-generated constructor stub
		this.packages = packs;
		this.classes = lc;
		this.path = path;
	}
	
	public Generer() {
		// TODO Auto-generated constructor stub
	}
	
	public void paintComponent(Graphics g) {
		try {
			//
			//trigger exception
			//int kk = 14/0;
			//	
			
			File packs = new File(path+"/src/");
			
			super.paintComponent(g);
			Graphics2D dessin = (Graphics2D) g;
			
			for (int i = 0; i < packages.length; i++) {
				dessin.setColor(Color.black);
				dessin.drawRect(10, (350*i)+10, 635, 350);
				dessin.drawString("Package: "+ packages[i], 20, (350*i)+25);
				
				List<String> lcc = new ArrayList<String>();
				
				File cls = new File(packs+"/"+packages[i]+"/");
				String[] claz = cls.list(new FilenameFilter() {
					  @Override
					  public boolean accept(File current, String name) {
					    return new File(current, name).isFile();
					  }
					});
				for (int jj = 0; jj < claz.length; jj++) {
					lcc.add(claz[jj]);
				}
				
				//new RuntimeException().printStackTrace(System.out);
				//System.out.println(Runtime.getRuntime().traceMethodCalls(true));
				
				List<String> allMeths = new ArrayList<String>();
				List<Integer> length = new ArrayList<Integer>();
				
				for (int j = 0; j < lcc.size(); j++) {
					
					File ff = new File(path+"/bin/");
					URL[] cp = {ff.toURI().toURL()};
					Class<?> c = new URLClassLoader(cp).loadClass(packages[i]+"."+lcc.get(j).substring(0, lcc.get(j).lastIndexOf('.')));
					
					dessin.setColor(Color.green);
					dessin.fillRect((350*j)+15, (350*i)+50, 150, 120);
					dessin.setColor(Color.black);
					dessin.drawString(c.getSimpleName()+".java", (350*j)+30, (350*i)+100);
					
					Method[] declaredMethodes = c.getDeclaredMethods();
					length.add(declaredMethodes.length);
					
					for (int k = 0; k < declaredMethodes.length; k++) {						 
						if (j==0) {
							dessin.setColor(Color.red);
							dessin.fillRect((350*j)+15+150, (350*i)+(25*k)+50+22, 10, 10);
							dessin.drawString(declaredMethodes[k].getName(), (350*j)+15+150, (350*i)+(25*k)+50+20);
						}else {
							dessin.setColor(Color.red);
							dessin.fillRect((350*j)+5, (350*i)+(25*k)+50+20, 10, 10);
							dessin.drawString(declaredMethodes[k].getName(), (350*j)+5, (350*i)+(25*k)+50+17);
						}
						allMeths.add(declaredMethodes[k].getName());
					}
					
					dessin.drawRect((350*j)+15, (350*i)+180, 150, 160);
					dessin.setColor(Color.black);
					dessin.drawString("Input: ", (350*j)+20, (350*i)+195);
					
					for (int k = 0; k < declaredMethodes.length; k++) {
						Class<?>[] parameterTypes = declaredMethodes[k].getParameterTypes();
						dessin.drawString(declaredMethodes[k].getName()+": "+ Arrays.toString(parameterTypes), (350*j)+25, (13*k)+(350*i)+215);
					}
					dessin.drawString("Output: ", (350*j)+20, (350*i)+275);
					
					for (int k = 0; k < declaredMethodes.length; k++) {
						dessin.drawString(declaredMethodes[k].getName()+": ["+ declaredMethodes[k].getReturnType().getSimpleName()+"]", (350*j)+25, (13*k)+(350*i)+295);
					}
		
				}
				
				/*
				---> links begin here
			    */
			
				for (int j = 0; j < lcc.size(); j++) {
					
					File fff = new File(path+"/src/"+packages[i]+"/"+lcc.get(j));
					List<String> finalRes = new ArrayList<String>();
					List<String> str = new ArrayList<String>();
					
					File f2 = new File(path+"/bin/");
					URL[] cpp = {f2.toURI().toURL()};
					Class<?> cc = new URLClassLoader(cpp).loadClass(packages[i]+"."+lcc.get(j).substring(0, lcc.get(j).lastIndexOf('.')));
					Method[] declaredMethodes2 = cc.getDeclaredMethods();
					
					for (Method m : declaredMethodes2) {
						str.add(m.getName());
					}
					
					BufferedReader br = new BufferedReader(new FileReader(fff));
					String currentLine = null;
					List<String> all = new ArrayList<String>();
					while ((currentLine = br.readLine()) != null) 
				    {
						String[] data = currentLine.split(" ");
						for (String meth : allMeths) {
							for (String string : data) {
								if (string.contains(meth)) {
									all.add(meth);
								}
							}
						}
				    }

					String head=all.get(0);
					String tail=all.get(0);
					for (int i3 = 0 ; i3 < all.size(); i3++) {
						if (str.contains(all.get(i3))) {
							head = all.get(i3);
							tail = all.get(i3);
						}else {
							tail=all.get(i3);
						}
						if (!(str.contains(head) && str.contains(tail))) {
							finalRes.add(head+" "+tail);
						}
					
					br.close();
				}
				
				dessin.setColor(Color.red);
				for (int jj = 0; jj < finalRes.size(); jj++) {
					int x1,x2,y1,y2 = 0;
					String[] dim = finalRes.get(jj).split(" ");
					//System.out.println(finalRes.toString());
					if (length.get(1)- length.get(0)==-1||length.get(1)- length.get(0)==1) {
						x1=15+150;
						y1=(350*i)+(25*( (allMeths.indexOf(dim[1]) )+1))+50;
						x2=(350)+15;
						y2=(350*i)+(25*( (allMeths.indexOf(dim[0])-length.get(1) )))+50;
					}else {
						if (length.get(1)- length.get(0)==-2||length.get(1)- length.get(0)==2) {
							x1=15+150;
							y1=(350*i)+(25*( (allMeths.indexOf(dim[1]) )+1))+50;
							x2=(350)+15;
							y2=(350*i)+(25*( (allMeths.indexOf(dim[0])-(length.get(0)-length.get(1))-1)))+50;
						}else {
							x1=15+150;
							y1=(350*i)+(25*( (allMeths.indexOf(dim[1]) )+1))+50;
							x2=(350)+15;
							y2=(350*i)+(25*( (allMeths.indexOf(dim[0])-(length.get(0)-length.get(1)))))+50;
						}
						
					}
					dessin.drawLine(x1, y1, x2, y2);
				}
			}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.toString());
			ExceptionSave s = new ExceptionSave();
			s.save(e);
		}finally {
			System.out.println("Jod_Done");
		}
	}
}
