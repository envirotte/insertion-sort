package org.simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*data.Node = package buatan sendiri*/

class Petunjuk extends JFrame
{
    private JTextArea area;

    public Petunjuk()
    {
        super("Petunjuk penggunaan");
        setSize(300,294);
        setVisible(true);
        setLayout(new BorderLayout());
        area=new JTextArea();
        area.setRows(16);
        area.setEditable(false);
        
        String isi =""+
                    "1. Untuk melakukan simulasi, pertama tekan input\n"+ 
                    "   dan input datanya lalu sort, setelah selesai\n"+ 
                    "   tekan reset.\n"+
                    "\n"+
                    "2. Untuk input, jika sudah menekan tombol input\n"+ 
                    "   namum tidak jadi, maka cukup dengan mengisi\n"+
                    "   input dengan sembarang angka [1-99] setelah\n"+ 
                    "   itu tekan reset untuk menghapus angka tadi.\n"+ 
                    "   Input data harus berupa angka [1-99] dan tidak\n"+ 
                    "   bisa kosong atau berupa huruf.\n"+
                    "\n"+
                    "3. Untuk sort, jika tombol sort sudah ditekan\n"+ 
                    "   maka simulasi akan berjalan dan tidak dapat\n"+ 
                    "   keluar sampai simulasi selesai. Sort dapat\n"+ 
                    "   dilakukan jika data sudah diinput.\n"+
                    "\n"+
                    "4. Untuk reset, jika tombol reset ditekan maka\n"+ 
                    "   semua data yang sudah diinput akan terhapus.";
        area.setText(isi);
        add(new JScrollPane(area),BorderLayout.NORTH);
    }
}

class InsertionSortPanel extends JPanel
{
    private Node node[] = new Node[6];
    /*new Color(0,255,51) = hijau*/
    private Node temp = new Node(0,20,70,10,50,new Color(0,255,51));
    private int n;
    private int pointer=0;
    private String kalimat="";
    private String keterangan="";
    
    public InsertionSortPanel()
    {
        node[0]=new Node();
        node[1]=new Node();
        node[2]=new Node();
        node[3]=new Node();
        node[4]=new Node();
        node[5]=new Node();
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        setSize(700,230);
        
        g.setColor(Color.BLACK);
        g.drawString(kalimat,10,200);
        g.drawString(keterangan,10,185);
        g.setColor(temp.getWarna());
        g.fillRect(temp.getPosKotakX(),temp.getPosKotakY(),30,30);
        g.setColor(Color.BLACK);
        g.drawString(temp.getAngka()+"",temp.getPosAngkaX(),temp.getPosAngkaY());
        g.drawString("Temp",10,100);
        for(int a=0;a<n;a++)
        {
            g.setColor(node[a].getWarna());
            g.fillRect(node[a].getPosKotakX(),node[a].getPosKotakY(),30,30);
            g.setColor(Color.BLACK);
            g.drawString(node[a].getAngka()+"",node[a].getPosAngkaX(),node[a].getPosAngkaY());
            g.drawString("["+a+"]",node[a].getPosKotakX()+10,node[a].getPosKotakY()+45);
        }  
        
        if(pointer>0)
        {
            showPointer(g);
            g.setColor(Color.BLACK);
            g.drawString("void insertionSort()",370,10);
            g.drawString("{",370,25);
            g.drawString("  int a,b,tempat_kosong,Temp;",370,40);
            g.drawString("  for(a=1;a<n;a++)",370,55);
            g.drawString("   {",370,70);
            g.drawString("   temp=arr[a];tempat_kosong=a;",370,85);
            g.drawString("   for(b=a-1;b>=0;b--)",370,100);
            g.drawString("       {",370,115);
            g.drawString("       if(temp < arr[b])",370,130);
            g.drawString("         {arr[b+1]=arr[b];arr[b]=0;tempat_kosong--;}",370,145);
            g.drawString("       }",370,160);
            g.drawString("   arr[tempat_kosong]=temp;temp=0;",370,175);
            g.drawString("   }",370,190);
            g.drawString("}",370,205);  
        }
    }
   
    public void showPointer(Graphics g)
    {
        int arrayX[]={355,365,355};
        int arrayY[]={pointer-5,pointer,pointer+5};
        g.setColor(new Color(153,153,255));
        g.fillPolygon(arrayX,arrayY,3);
    }
    
    public void setNode(Node node,int index)
    {
        this.node[index] = node;
    }
    
    public void setN(int n)
    {
        this.n=n;
    }
    
    public void setKalimat(String kal)
    {
        this.kalimat=kal;
    }
    
    public void setTemp(int x)
    {
        temp.setAngka(x);
    }
    
    public void setPointer(int pointer)
    {
        this.pointer=pointer;
    }
    
    public void setKeterangan(int a,int b,int tmp,boolean status)
    {
        if(status==true)
        {
            keterangan="a="+a+" b="+b+" tempat_kosong="+tmp;
        }
        else
        {
            keterangan="";
        }
    }
    
    public Node getNode(int index)
    {
        return node[index];
    }
    
    public int getN()
    {
        return n;
    }
    
    public String getKalimat()
    {
        return kalimat;
    }
    
    public int getTemp()
    {
        return temp.getAngka();
    }
    
    public int getPointer()
    {
        return pointer;
    }
}

class InsertionSortFrame extends JFrame
{
    private JButton input = new JButton("Input");
    private JButton sort = new JButton("Sort");
    private JButton reset = new JButton("Reset");
    private JButton help = new JButton("Help");
    private InsertionSortPanel panel = new InsertionSortPanel();
    private JPanel buttonPanel = new JPanel();
    private BorderLayout tampilan = new BorderLayout();
    private Node node[] = new Node[6];    
    private int n;
    private boolean statusInput;
    private boolean statusSort;
    /*untuk status true=siap diexecute, false=sedang dijalankan*/
    
    public InsertionSortFrame()
    {
        super("Simulasi Insertion Sort");
        statusInput=true;
        statusSort=true;
        node[0]=new Node();
        node[1]=new Node();
        node[2]=new Node();
        node[3]=new Node();
        node[4]=new Node();
        node[5]=new Node();
        input.setToolTipText("Input data");
        sort.setToolTipText("Sort data");
        reset.setToolTipText("Hapus semua data");
        help.setToolTipText("Petunjuk penggunaan");
        setLayout(tampilan);
        
        add(panel,BorderLayout.NORTH);
            
        input.addActionListener(new h());
        sort.addActionListener(new h());
        reset.addActionListener(new h());
        help.addActionListener(new h());
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(input);
        buttonPanel.add(sort);
        buttonPanel.add(reset);
        buttonPanel.add(help);
        add(buttonPanel,BorderLayout.SOUTH);
    }
    
    public void inputData()
    { 
        /*validasi banyak angka*/
        do{
            String a=JOptionPane.showInputDialog(null,"Masukkan banyak angka [1-6]","Jumlah Bilangan",JOptionPane.PLAIN_MESSAGE);    
            try{
                n=Integer.parseInt(a);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Bukan angka!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);        
            }
        
        if(n>=1 && n<=6)
            {break;}
        }while(true);
        
        /*validasi input angka*/
        for(int c=1;c<=n;c++)
        {
            int x = 0;
            do{
                String b=JOptionPane.showInputDialog(null,"Masukan angka ke-"+c+" [1-99]","Input",JOptionPane.PLAIN_MESSAGE);
                try
                    {
                    x=Integer.parseInt(b);
                    }
                catch(Exception e)
                    {
                    JOptionPane.showMessageDialog(null,"Bukan angka!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);      
                    }
            if(x>=1 && x<=99)
                {break;}
            }while(true);
                  
            node[c-1].setAngka(x);
            node[c-1].setPosAngkaX(55+ 35*(c-1));
            node[c-1].setPosAngkaY(70);
            node[c-1].setPosKotakX(45+ 35*(c-1));
            node[c-1].setPosKotakY(50);       
        }
        
        /*status jadi false supaya tdk bisa input ulang*/
        statusInput=false;
    }
    
    public void insertionSort()
    {
        int a,b,tempat_kosong,temp;
        statusSort=false;
        
        /*inisialisasi panel*/
        panel.setN(n);
        panel.setKalimat("");
        panel.setPointer(35);
        for(a=0;a<n;a++)
        {
            panel.setNode(node[a],a);
        }
        panel.repaint();repaint();
        /*pemberitahuan*/
        JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);
        
        for(a=1;a<n;a++)
        {
            temp=node[a].getAngka();
            tempat_kosong=a;
            node[a].setAngka(0);
            panel.setTemp(temp);
            panel.setNode(node[a],a);
            panel.setKalimat("Temp = "+temp);
            panel.setPointer(50);
            panel.repaint();repaint();
            
            /*pemberitahuan*/
            JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);
            
            for(b=a-1;b>=0;b--)
            {
                node[b].setWarna(Color.RED);
                panel.setNode(node[b],b);
                panel.setPointer(125);
                panel.setKalimat("jika temp < "+node[b].getAngka()+" maka "+node[b].getAngka()+" geser kekanan");
                panel.setKeterangan(a,b,tempat_kosong,true);
                panel.repaint();repaint();
                /*pemberitahuan*/
                JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);
                
                if(temp<node[b].getAngka())
                {
                    node[b+1].setAngka(node[b].getAngka());
                    node[b].setAngka(0);
                    tempat_kosong--;
                }
                
                node[b].setWarna(Color.YELLOW);
                panel.setNode(node[b],b);
                panel.setKalimat("");
                panel.repaint();repaint();
            }
            
            /*pemberitahuan*/
            JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);
            
            node[tempat_kosong].setAngka(temp);
            panel.setNode(node[tempat_kosong],tempat_kosong);
            panel.setKalimat("Temp pindah ke tempat yang kosong (0)");
            temp=0; 
            panel.setTemp(0);
            panel.setPointer(170);
            panel.repaint();repaint();
            
            /*pemberitahuan*/
            JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);
        }
        
        panel.setKalimat("Insertion sort selesai");
        panel.setKeterangan(0,0,0,false);
        panel.setPointer(200);
        panel.repaint();repaint();
        statusSort=true;
    }
    
    public void hapusData()
    {
        statusInput=true;
        statusSort=true;
        n=0;
        node[0]=new Node();
        node[1]=new Node();
        node[2]=new Node();
        node[3]=new Node();
        node[4]=new Node();
        node[5]=new Node();
        panel.setN(n);
        panel.setBackground(Color.WHITE);
        panel.setKalimat("");
        panel.setKeterangan(0,0,0,false);
        panel.setPointer(0);
        panel.repaint();
        repaint();
    }
    
    public void tampilPetunjuk()
    {
        Petunjuk show=new Petunjuk();
    }
    
    private class h implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==input)
            {
                if(statusInput==true)
                    {inputData();}
                else
                    {JOptionPane.showMessageDialog(null,"Sudah diinput!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);}
            }
            
            if(e.getSource()==sort)
            {
                if(statusSort==true)
                    {
                    if(statusInput==false)
                        {
                        insertionSort();
                        }
                    else
                        {
                        JOptionPane.showMessageDialog(null,"Belum input data!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                else
                    {JOptionPane.showMessageDialog(null,"Sedang disort!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);}
            }
            
            if(e.getSource()==reset)
            {
                hapusData(); 
            }
            
            if(e.getSource()==help)
            {
                tampilPetunjuk();
            }
        }
    }
}

public class InsertionSortSimulation
{
    public static void main(String args[])
    {
        InsertionSortFrame insertion = new InsertionSortFrame();
        insertion.setSize(700,300);
        insertion.setVisible(true);
        insertion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
