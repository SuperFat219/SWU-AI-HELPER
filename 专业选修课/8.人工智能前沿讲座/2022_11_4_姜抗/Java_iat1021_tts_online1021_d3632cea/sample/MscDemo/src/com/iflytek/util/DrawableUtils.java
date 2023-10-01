package com.iflytek.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DrawableUtils {
	
	public static JButton createImageButton(String name, ImageIcon img, String layout)
	{
		JButton button = new JButton(img);
		button.setSize(img.getIconWidth(), img.getIconHeight());
		button.setBackground(null);
		
		button.setBorder(null);
		button.setContentAreaFilled(false);
		
		JLabel label = new JLabel(name, SwingConstants.CENTER);
		label.setForeground(Color.white);
		Font font=new Font("宋体", Font.BOLD, 30);
		label.setFont(font);
		
		button.setLayout(new BorderLayout());
		
		if(layout == null || layout.contains("center"))
			button.add(label, BorderLayout.CENTER);
		else if(layout.equals("right") || layout.contains("right"))
		{
			button.add(label, BorderLayout.EAST);
		}
		
		return button;
	}
	
	public static int  getScreenWidth() {
		return (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
	}

	public static int getScreenHeight() {
		return (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
	}
	
	public static void setMouseListener(final JButton button, String iconPath) {
		
		final ImageIcon imagePressed = new ImageIcon(iconPath + "_p.png");
		final ImageIcon image= new ImageIcon(iconPath + ".png");
		
		button.addMouseListener(new MouseAdapter() {   
            public void mousePressed(MouseEvent e) {   
            	button.setIcon(imagePressed);
            }   
  
            public void mouseReleased(MouseEvent e) {   
                button.setIcon(image);
            }   
        });  	
	}
	
	public static Image divisionImage(Image backImg, int x, int y, int width,
			int height) {
//		Image img = getSpaceImage(width, height, null);
		Image img = backImg;
		img.getGraphics().drawImage(backImg, 0, 0, width, height, x, y,
				x + width, y + height, null);
		return img;
	}
}
