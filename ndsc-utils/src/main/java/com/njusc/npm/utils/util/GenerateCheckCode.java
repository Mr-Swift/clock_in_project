package com.njusc.npm.utils.util;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.servlet.http.HttpSession;

public class GenerateCheckCode
{
    public static final String CHECK_CODE_KEY = "check_code";
    public static final int WIDTH = 80;
    public static final int HEIGHT = 34;
    
    /**
     * <p>取得验证码图片，并把生成的数字放到HttpSession中</p>
     * @return
     * @author:王恺
     * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    public static BufferedImage getImage(HttpSession session)
    {
        //创建内存图象并获得其图形上下文
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        //产生随机的认证码
        char[] rands = generateCheckCode();
        //产生图像
        drawBackground(g);
        drawRands(g, rands);
        //结束图像的绘制过程，完成图像
        g.dispose();
        //将当前验证码存入到Session中
        session.setAttribute(CHECK_CODE_KEY, new String(rands));
        return image;
    }
    
    /**
     * <p>随机产生验证码字符</p>
     * @return
     * @author:王恺
     * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    public static char[] generateCheckCode()
    {
        //定义验证码的字符表  去掉了i,l ,0, o
        String chars = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++)
        {
            int rand = (int) (Math.random() * chars.length());
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    /**
     * <p>字符位置、字体属性</p>
     * @param g
     * @param rands
     * @author:王恺
     * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    public static void drawRands(Graphics g, char[] rands)
    {
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 28));
        //在不同的高度上输出验证码的每个字符 
        g.drawString("" + rands[0], 5, 25);
        g.drawString("" + rands[1], 20, 25);
        g.drawString("" + rands[2], 35, 28);
        g.drawString("" + rands[3], 50, 26);
        //System.out.println(rands);
    }

    /**
     * <p>背景</p>
     * @param g
     * @author:王恺
     * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    public static void drawBackground(Graphics g)
    {
        //画背景
//        g.setColor(new Color(0xDCDCDC));
    	 g.setColor(Color.white);
         g.fillRect(0, 0, WIDTH, HEIGHT);
        //随机产生120个干扰点
        for (int i = 0; i < 500; i++)
        {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);
        }
        //画4条直线
        for (int i = 0; i < 50; i++)
        {
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
            g.drawLine((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT), (int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));
        }
    }
}