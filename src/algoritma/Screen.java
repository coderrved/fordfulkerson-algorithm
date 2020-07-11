/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritma;

import static algoritma.Arayüz.maksYollariAt;
import static algoritma.Arayüz.yeniArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author HP
 */
public class Screen extends javax.swing.JFrame {

    Image img;
    Graphics gfx;
    int nodeSize = 50;
    int xKordinat = 30, yKordinat = 20;
    int sifirinciNodeXKordinati = 0, sifirinciNodeYKordinati = 250,
            birinciNodeXKordinati = 100, birinciNodeYKordinati = 175,
            ikinciNodeXKordinati = 100, ikinciNodeYKordinati = 325;
    int sayacTek = 1, sayacCift = 1;
    int nodeSayisi, baslangicNode = 0, bitisNode = 0;
    ArrayList<Integer> pipeLineInfo = new ArrayList<>();
    ArrayList<Integer> pipeLineKapasite = new ArrayList<>();
    ArrayList<Integer> nodeKordinatTut = new ArrayList<>();
    boolean maksButtonClicked = false, orjinalButtonClicked = true, minimumButtonClicked = false;
    boolean sonlandir = false;
    int minCutDeger = 0, maksFlowDeger = 0, maksButtonSayac = 0;
    int girintiTek = 0, girintiCift = 0;

    public Screen() {
        initComponents();
        setBackground(Color.white);
        img = panel.createImage(panel.getWidth(), panel.getHeight());
        gfx = (Graphics2D) img.getGraphics();

    }

    public Screen(int nodeSayisi, ArrayList<Integer> pipeLineInfo, ArrayList<Integer> pipeLineKapasite, int baslangicNode, int bitisNode) {
        initComponents();
        setBackground(Color.white);
        img = panel.createImage(panel.getWidth(), panel.getHeight());
        gfx = (Graphics2D) img.getGraphics();
        this.bitisNode = bitisNode;
        this.nodeSayisi = nodeSayisi;
        this.pipeLineInfo = pipeLineInfo;
        this.pipeLineKapasite = pipeLineKapasite;
        this.baslangicNode = baslangicNode;
    }

    public void draw(int deger, int deger2, int i) {
        if (i == baslangicNode) {
            gfx.setColor(Color.blue);
        } else if (i == bitisNode) {
            gfx.setColor(Color.pink);
        } else {
            gfx.setColor(Color.black);
        }
        gfx.fillOval(xKordinat + deger, yKordinat + deger2, nodeSize, nodeSize);
        gfx.setColor(Color.red);
        gfx.setFont(new Font("DialogInput", Font.BOLD, 18));
        gfx.drawString(String.valueOf(i), xKordinat + deger + 21, yKordinat + deger2 + 30);
        panel.getGraphics().drawImage(img, 0, 0, this);
    }

    public void drawTemizle() {
        gfx.setColor(Color.white);
        gfx.fillRect(580, 610, 10, 25);
    }

    public void drawSabit(int i) {
        if (i == baslangicNode) {
            gfx.setColor(Color.blue);
            gfx.fillOval(0, 550, nodeSize, nodeSize);
            gfx.setFont(new Font("DialogInput", Font.BOLD, 18));
            gfx.drawString(": Başlangiç Düğüm", 60, 580);
        } else {
            gfx.setColor(Color.pink);
            gfx.fillOval(0, 600, nodeSize, nodeSize);
            gfx.setFont(new Font("DialogInput", Font.BOLD, 18));
            gfx.drawString(": Bitiş Düğüm", 60, 630);
        }
        panel.getGraphics().drawImage(img, 0, 0, this);
    }

    public void drawMaks(int maks) {
        gfx.setColor(Color.black);
        gfx.drawString("/" + String.valueOf(maks), 590, 630);
    }

    public void drawMaksOn(int maks) {
        gfx.setColor(Color.black);
        gfx.setFont(new Font("DialogInput", Font.BOLD, 18));
        gfx.drawString(String.valueOf(maks), 580, 630);
    }

    public void cizgiCek(int toNode, int fromNode) {
        gfx.setColor(Color.black);
        System.out.println("Cizgi Cek Fonksiyoununa giris yapti");
        System.out.println("toNode: " + toNode + " toNode*2: " + String.valueOf(toNode * 2) + " fromNode: " + fromNode + " fromNode*2: " + (fromNode * 2));
        gfx.drawLine(nodeKordinatTut.get(toNode * 2) + nodeSize, nodeKordinatTut.get((toNode * 2) + 1) + nodeSize, nodeKordinatTut.get(fromNode * 2) + nodeSize, nodeKordinatTut.get((fromNode * 2) + 1) + nodeSize);
        panel.getGraphics().drawImage(img, 0, 0, this);
    }

    public void ustundenCizgiCek(int toNode, int fromNode) {
        gfx.setColor(Color.red);
        System.out.println("Ustunden gec fonksiyonuna girdi");
        gfx.drawLine(nodeKordinatTut.get(toNode * 2) + nodeSize, nodeKordinatTut.get((toNode * 2) + 1) + nodeSize, nodeKordinatTut.get(fromNode * 2) + nodeSize, nodeKordinatTut.get((fromNode * 2) + 1) + nodeSize);
        panel.getGraphics().drawImage(img, 0, 0, this);
    }

    public void ustundenCizgiCek1(int toNode, int fromNode) {
        gfx.setColor(Color.green);
        System.out.println("Ustunden gec fonksiyonuna girdi");
        gfx.drawLine(nodeKordinatTut.get(toNode * 2) + nodeSize, nodeKordinatTut.get((toNode * 2) + 1) + nodeSize, nodeKordinatTut.get(fromNode * 2) + nodeSize, nodeKordinatTut.get((fromNode * 2) + 1) + nodeSize);
        panel.getGraphics().drawImage(img, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        for (int i = 0; i < nodeSayisi; i++) {
            if (i == 0) {
                System.out.println("SİFİRİNCİ NODE");
                nodeKordinatTut.add(sifirinciNodeXKordinati);
                nodeKordinatTut.add(sifirinciNodeYKordinati);
                draw(sifirinciNodeXKordinati, sifirinciNodeYKordinati, 0);
            } else if (i == 1) {
                System.out.println("BİRİNCİ NODE");
                nodeKordinatTut.add(birinciNodeXKordinati);
                nodeKordinatTut.add(birinciNodeYKordinati);
                draw(birinciNodeXKordinati, birinciNodeYKordinati, i);
            } else if (i == 2) {
                System.out.println("İKİNCİ NODE");
                nodeKordinatTut.add(ikinciNodeXKordinati);
                nodeKordinatTut.add(ikinciNodeYKordinati);
                draw(ikinciNodeXKordinati, ikinciNodeYKordinati, i);
            } else {
                System.out.println("else içine girdi");
                if ((i + 1) == nodeSayisi) {
                    System.out.println("graph sonuna gelindi");
                    if ((i + 1) % 2 != 0) { // node tek sayida
                        System.out.println("Node Tek Sayida");
                        System.out.println("Node Çift Sayida");
                        nodeKordinatTut.add(birinciNodeXKordinati + 20 + (100 * (sayacTek)));
                        nodeKordinatTut.add(sifirinciNodeYKordinati);
                        draw(birinciNodeXKordinati + 20 + (100 * (sayacTek)), sifirinciNodeYKordinati, i);
                        break;
                    } else { // node çift sayida
                        System.out.println("Node Çift Sayida");
                        nodeKordinatTut.add(birinciNodeXKordinati + 20 + (100 * (sayacTek)));
                        nodeKordinatTut.add(sifirinciNodeYKordinati);
                        draw(birinciNodeXKordinati + 20 + (100 * (sayacTek)), sifirinciNodeYKordinati, i);
                        break;
                    }
                }

                if (i % 2 != 0) {
                    if (girintiTek % 2 == 0) {
                        girintiTek++;
                        System.out.println("Tek içine girdi - girintiTek: " + girintiTek);
                        nodeKordinatTut.add(birinciNodeXKordinati + (100 * (sayacTek)));
                        nodeKordinatTut.add(birinciNodeYKordinati - 170);
                        draw(birinciNodeXKordinati + (100 * (sayacTek)), birinciNodeYKordinati - 150, i);
                        sayacTek++;
                    } else {
                        girintiTek++;
                        System.out.println("Tek içine girdi - girintiTek: " + girintiTek);
                        nodeKordinatTut.add(birinciNodeXKordinati + (100 * (sayacTek)));
                        nodeKordinatTut.add(birinciNodeYKordinati);
                        draw(birinciNodeXKordinati + (100 * (sayacTek)), birinciNodeYKordinati, i);
                        sayacTek++;
                    }
                }
                if (i % 2 == 0) {
                    if (girintiCift % 2 == 0) {
                        girintiCift++;
                        System.out.println("Cift içine girdi");
                        nodeKordinatTut.add(ikinciNodeXKordinati + (100 * (sayacCift)));
                        nodeKordinatTut.add(ikinciNodeYKordinati + 150);
                        draw(ikinciNodeXKordinati + (100 * (sayacCift)), ikinciNodeYKordinati + 150, i);
                        sayacCift++;
                    } else {
                        girintiCift++;
                        System.out.println("Cift içine girdi");
                        nodeKordinatTut.add(ikinciNodeXKordinati + (100 * (sayacCift)));
                        nodeKordinatTut.add(ikinciNodeYKordinati);
                        draw(ikinciNodeXKordinati + (100 * (sayacCift)), ikinciNodeYKordinati, i);
                        sayacCift++;
                    }

                }
            }
            System.out.println("FOR DÖNDÜ");
        }

        for (int y = 0; y < pipeLineInfo.size(); y += 2) {  // Nodelar arasındaki köprüyü kurmak için kullandığım for.
            System.out.println("Cizgi Cek Foruna girdi..");
            cizgiCek(pipeLineInfo.get(y), pipeLineInfo.get(y + 1));
        }

        sayacTek = 1;
        sayacCift = 1;
        girintiCift=0;
        girintiTek=0;
        for (int i = 0; i < nodeSayisi; i++) {  // Yukarıdaki node çizen fordan farkı yok sadece çizgiler node üstünde değil altında kalsın diye bir daha bastırdım.
            if (i == 0) {
                draw(sifirinciNodeXKordinati, sifirinciNodeYKordinati, 0);
            } else if (i == 1) {
                draw(birinciNodeXKordinati, birinciNodeYKordinati, i);
            } else if (i == 2) {
                draw(ikinciNodeXKordinati, ikinciNodeYKordinati, i);
            } else {
                if ((i + 1) == nodeSayisi) {
                    if ((i + 1) % 2 != 0) { // node tek sayida  
                        draw(birinciNodeXKordinati + 20 + (100 * (sayacTek)), sifirinciNodeYKordinati, i);
                        break;
                    } else { // node çift sayida                        
                        draw(birinciNodeXKordinati + 20 + (100 * (sayacTek)), sifirinciNodeYKordinati, i);
                        break;
                    }
                }
                if (i % 2 != 0) {
                    if (girintiTek % 2 == 0) {
                        girintiTek++;                        
                        draw(birinciNodeXKordinati + (100 * (sayacTek)), birinciNodeYKordinati - 150, i);
                        sayacTek++;
                    } else {
                        girintiTek++;                        
                        draw(birinciNodeXKordinati + (100 * (sayacTek)), birinciNodeYKordinati, i);
                        sayacTek++;
                    }                  
                }
                if (i % 2 == 0) {
                    if (girintiCift % 2 == 0) {
                        girintiCift++;                      
                        draw(ikinciNodeXKordinati + (100 * (sayacCift)), ikinciNodeYKordinati + 150, i);
                        sayacCift++;
                    } else {
                        girintiCift++;                       
                        draw(ikinciNodeXKordinati + (100 * (sayacCift)), ikinciNodeYKordinati, i);
                        sayacCift++;
                    }
                }
            }
        }

        

         drawSabit(baslangicNode);
         drawSabit(bitisNode);
         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        minimumButton = new javax.swing.JButton();
        orjinalButton = new javax.swing.JButton();
        maksButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 667, Short.MAX_VALUE)
        );

        minimumButton.setBackground(new java.awt.Color(153, 255, 153));
        minimumButton.setForeground(new java.awt.Color(255, 153, 0));
        minimumButton.setText("MİNİMUM KESİLMESİ GEREK KÖPRÜ BİLGİSİ ");
        minimumButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimumButtonActionPerformed(evt);
            }
        });

        orjinalButton.setBackground(new java.awt.Color(153, 255, 153));
        orjinalButton.setForeground(new java.awt.Color(255, 153, 0));
        orjinalButton.setText("ORJİNAL HALİNİ GOSTER");
        orjinalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orjinalButtonActionPerformed(evt);
            }
        });

        maksButton.setBackground(new java.awt.Color(153, 255, 153));
        maksButton.setForeground(new java.awt.Color(255, 153, 0));
        maksButton.setText("MAKSİMUM AKIŞ OLAN YOLLARI GÖSTER");
        maksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maksButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(minimumButton)
                .addGap(97, 97, 97)
                .addComponent(maksButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(orjinalButton)
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minimumButton)
                    .addComponent(maksButton)
                    .addComponent(orjinalButton))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void maksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maksButtonActionPerformed

        if (!orjinalButtonClicked) {
            maksButtonSayac = 0;
            orjinalButtonClicked = true;
            drawTemizle();
            drawMaksOn(maksButtonSayac);
        }
        System.out.println("basma sirasi size: " + Arayüz.basmaSirasi.size());
        if (maksButtonSayac + 1 < Arayüz.basmaSirasi.size()) {
            try {
                for (int f = Arayüz.basmaSirasi.get(maksButtonSayac); f < Arayüz.basmaSirasi.get(maksButtonSayac + 1); f += 2) {
                    ustundenCizgiCek(maksYollariAt.get(f), maksYollariAt.get(f + 1));
                    System.out.println("if f: " + (f) + " f+1: " + (f + 1));
                }
            } catch (Exception e) {
                System.out.println("İF Error");
            }
        } else {
            try {

                for (int f = Arayüz.basmaSirasi.get(maksButtonSayac); f < Arayüz.maksYollariAt.size(); f += 2) {

                    ustundenCizgiCek(maksYollariAt.get(f), maksYollariAt.get(f + 1));
                    System.out.println("else f: " + (f) + " f+1: " + (f + 1));
                }
            } catch (Exception e) {
                System.out.println("ELSE Error");
            }

        }

        maksButtonSayac++;

        System.out.println("Sayac: " + Arayüz.sayac);
        if (maksButtonSayac <= Arayüz.sayac) {
            drawTemizle();
            drawMaksOn(maksButtonSayac);
        }
        drawMaks(Arayüz.sayac);

        sayacTek = 1;
        sayacCift = 1;
        girintiCift=0;
        girintiTek=0;
        
        for (int i = 0; i < nodeSayisi; i++) {  // Yukarıdaki node çizen fordan farkı yok sadece çizgiler node üstünde değil altında kalsın diye bir daha bastırdım.
            if (i == 0) {
                draw(sifirinciNodeXKordinati, sifirinciNodeYKordinati, 0);
            } else if (i == 1) {
                draw(birinciNodeXKordinati, birinciNodeYKordinati, i);
            } else if (i == 2) {
                draw(ikinciNodeXKordinati, ikinciNodeYKordinati, i);
            } else {
                if ((i + 1) == nodeSayisi) {
                    if ((i + 1) % 2 != 0) { // node tek sayida  
                        draw(birinciNodeXKordinati + 20 + (100 * (sayacTek)), sifirinciNodeYKordinati, i);
                        break;
                    } else { // node çift sayida                        
                        draw(birinciNodeXKordinati + 20 + (100 * (sayacTek)), sifirinciNodeYKordinati, i);
                        break;
                    }
                }
                if (i % 2 != 0) {
                    if (girintiTek % 2 == 0) {
                        girintiTek++;                        
                        draw(birinciNodeXKordinati + (100 * (sayacTek)), birinciNodeYKordinati - 150, i);
                        sayacTek++;
                    } else {
                        girintiTek++;                        
                        draw(birinciNodeXKordinati + (100 * (sayacTek)), birinciNodeYKordinati, i);
                        sayacTek++;
                    }                  
                }
                if (i % 2 == 0) {
                    if (girintiCift % 2 == 0) {
                        girintiCift++;                      
                        draw(ikinciNodeXKordinati + (100 * (sayacCift)), ikinciNodeYKordinati + 150, i);
                        sayacCift++;
                    } else {
                        girintiCift++;                       
                        draw(ikinciNodeXKordinati + (100 * (sayacCift)), ikinciNodeYKordinati, i);
                        sayacCift++;
                    }
                }
            }
        }

        maksFlowDeger += 1;
        if (maksFlowDeger == 1) {
            MaksimumFlowYolBilgisi mfyb = new MaksimumFlowYolBilgisi();
            mfyb.setVisible(true);
        }

    }//GEN-LAST:event_maksButtonActionPerformed

    private void minimumButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimumButtonActionPerformed

        for (int k = 0; k < yeniArrayList.size(); k += 2) { // Hangi kenarların olduklarını bu for yardımı ile gösterdim.
            ustundenCizgiCek1(yeniArrayList.get(k), yeniArrayList.get(k + 1));
        }

        sayacTek = 1;
        sayacCift = 1;
        girintiCift=0;
        girintiTek=0;
        for (int i = 0; i < nodeSayisi; i++) {  // Yukarıdaki node çizen fordan farkı yok sadece çizgiler node üstünde değil altında kalsın diye bir daha bastırdım.
            if (i == 0) {
                draw(sifirinciNodeXKordinati, sifirinciNodeYKordinati, 0);
            } else if (i == 1) {
                draw(birinciNodeXKordinati, birinciNodeYKordinati, i);
            } else if (i == 2) {
                draw(ikinciNodeXKordinati, ikinciNodeYKordinati, i);
            } else {
                if ((i + 1) == nodeSayisi) {
                    if ((i + 1) % 2 != 0) { // node tek sayida  
                        draw(birinciNodeXKordinati + 20 + (100 * (sayacTek)), sifirinciNodeYKordinati, i);
                        break;
                    } else { // node çift sayida                        
                        draw(birinciNodeXKordinati + 20 + (100 * (sayacTek)), sifirinciNodeYKordinati, i);
                        break;
                    }
                }
                if (i % 2 != 0) {
                    if (girintiTek % 2 == 0) {
                        girintiTek++;                        
                        draw(birinciNodeXKordinati + (100 * (sayacTek)), birinciNodeYKordinati - 150, i);
                        sayacTek++;
                    } else {
                        girintiTek++;                        
                        draw(birinciNodeXKordinati + (100 * (sayacTek)), birinciNodeYKordinati, i);
                        sayacTek++;
                    }                  
                }
                if (i % 2 == 0) {
                    if (girintiCift % 2 == 0) {
                        girintiCift++;                      
                        draw(ikinciNodeXKordinati + (100 * (sayacCift)), ikinciNodeYKordinati + 150, i);
                        sayacCift++;
                    } else {
                        girintiCift++;                       
                        draw(ikinciNodeXKordinati + (100 * (sayacCift)), ikinciNodeYKordinati, i);
                        sayacCift++;
                    }
                }
            }
        }

        minCutDeger += 1;

        if (minCutDeger == 1) {
            MinCutYolBilgisi mcyb = new MinCutYolBilgisi();
            mcyb.setVisible(true);
        }

        //mcyb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_minimumButtonActionPerformed

    private void orjinalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orjinalButtonActionPerformed

        drawTemizle();
        drawMaksOn(0);

        for (int y = 0; y < pipeLineInfo.size(); y += 2) {  // Nodelar arasındaki köprüyü kurmak için kullandığım for.
            System.out.println("Cizgi Cek Foruna girdi..");
            cizgiCek(pipeLineInfo.get(y), pipeLineInfo.get(y + 1));
        }

        sayacTek = 1;
        sayacCift = 1;
        girintiCift=0;
        girintiTek=0;
        for (int i = 0; i < nodeSayisi; i++) {  // Yukarıdaki node çizen fordan farkı yok sadece çizgiler node üstünde değil altında kalsın diye bir daha bastırdım.
            if (i == 0) {
                draw(sifirinciNodeXKordinati, sifirinciNodeYKordinati, 0);
            } else if (i == 1) {
                draw(birinciNodeXKordinati, birinciNodeYKordinati, i);
            } else if (i == 2) {
                draw(ikinciNodeXKordinati, ikinciNodeYKordinati, i);
            } else {
                if ((i + 1) == nodeSayisi) {
                    if ((i + 1) % 2 != 0) { // node tek sayida  
                        draw(birinciNodeXKordinati + 20 + (100 * (sayacTek)), sifirinciNodeYKordinati, i);
                        break;
                    } else { // node çift sayida                        
                        draw(birinciNodeXKordinati + 20 + (100 * (sayacTek)), sifirinciNodeYKordinati, i);
                        break;
                    }
                }
                if (i % 2 != 0) {
                    if (girintiTek % 2 == 0) {
                        girintiTek++;                        
                        draw(birinciNodeXKordinati + (100 * (sayacTek)), birinciNodeYKordinati - 150, i);
                        sayacTek++;
                    } else {
                        girintiTek++;                        
                        draw(birinciNodeXKordinati + (100 * (sayacTek)), birinciNodeYKordinati, i);
                        sayacTek++;
                    }                  
                }
                if (i % 2 == 0) {
                    if (girintiCift % 2 == 0) {
                        girintiCift++;                      
                        draw(ikinciNodeXKordinati + (100 * (sayacCift)), ikinciNodeYKordinati + 150, i);
                        sayacCift++;
                    } else {
                        girintiCift++;                       
                        draw(ikinciNodeXKordinati + (100 * (sayacCift)), ikinciNodeYKordinati, i);
                        sayacCift++;
                    }
                }
            }
        }
        orjinalButtonClicked = false;
    }//GEN-LAST:event_orjinalButtonActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //Screen screen = new Screen();
                new Screen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton maksButton;
    private javax.swing.JButton minimumButton;
    private javax.swing.JButton orjinalButton;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
