/*
 * PEightQueen.java
 *
 * Created on August 28, 2007, 8:59 PM
 *
 */

import javax.swing.*;

/**
 *
 * @author Tony Lucento
 */
public class PEightQueen extends JApplet {
    
    public static final long serialVersionUID = 1L;
                
    private Board board;
    
    private java.util.HashSet<Integer> solution;
    
    /** Creates a new instance of PEightQueen */
    public PEightQueen() {
        board = new Board(8);
        solution = new java.util.HashSet<Integer>();
    }
    
    public java.util.HashSet<Integer> getSolution(){
        return solution;
    }
    
    public boolean findSolution(int sqId){
        int col = board.getSquare(sqId).getColumn();
		
        for (int row = 0; row < 8; row++) { 

            if (board.getSquare(row * 8 + col).getSafe()) {
                placeQueen(row * 8 + col);
                
                if (solution.size() == 8) {
                    return true; 
                } 
                else {

                    if (findSolution(sqId + 1)) {
                        return true; 
                    } 
                    else {
                        removeQueen(row * 8 + col); 
                    }
                    
                }
                
            }
            else{
                //System.out.println(row * 8 + col + " was not safe");
            }
                
            
        }
        
        return false;
    }
    
    private void placeQueen(int sqId) {
     
        //System.out.println("Placing Q " + sqId);
        
        board.getSquare(sqId).setSafe(false);
        solution.add(sqId);
        
        board.setUnsafeSquares(solution);
        
    }
   
    private void removeQueen(int sqId) {
       
        //System.out.println("Removing Q " + sqId);
        
        board.getSquare(sqId).setSafe(true);
        solution.remove(sqId);
        
        board.setUnsafeSquares(solution);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ChessboardJFrame frame = new ChessboardJFrame();
        frame.setVisible(true); 
        
    }

    public class Board {
        int dim;
        java.util.ArrayList<Square> squares;
        
        public Board(int dimension) {
            dim = dimension;
            
            squares = new java.util.ArrayList<Square>();
            
            int id = 1;
            
            for (id = 1; id <= dim*dim; id++) {
                Square square = new Square(this, id);
                squares.add(square);
            }
            
        }
        
        public int getDimension(){
            return dim;
        }
        
        public int getSquareCount(){
            return dim * dim;
        }
        
        public java.util.ArrayList<Square> getSquares() {
            return squares;
        }
        
        public Square getSquare(int id) {
            return squares.get(id - 1);
        }
        
        public void setUnsafeSquares(java.util.HashSet<Integer> occupied){
            for (Square s : squares){
                s.setSafe(true);
            }
            
            
            
            java.util.Iterator<Integer> it = occupied.iterator();
            
            while (it.hasNext()){
                board.getSquare(it.next()).setVulnerablesUnsafe();
            }
            
        }
    }
    
    public class Square  {
               
        int id;
        boolean safe;
        Board board;
        
        public Square(Board board, int id){
            this.id = id;
            this.board = board;
            this.safe = true;
        }
        
        public int getId() {
            return id;
        }
        
        public int getRow(){
            return id % board.getDimension() == 0 
                    ? id / board.getDimension() 
                    : id / board.getDimension() + 1;
        }
        
        public int getColumn(){
            return id % board.getDimension() == 0 
                    ? board.getDimension() 
                    : id % board.getDimension();
        }
    
        public boolean getSafe(){
            return safe;
        }
        
        public void setSafe(boolean safe){
            this.safe = safe;
        }
            
        public void setVulnerablesUnsafe(){
            safe = false;
            int dim = board.getDimension();
            int row = getRow();
            int col = getColumn();
            
            //System.out.println("Unsafe squares from  " + id + " are:");
            
            //same row
            for (int i = row * dim; i > (row - 1) * dim; i--){
                board.getSquare(i).setSafe(false);
                //System.out.print(" " + i);
            }
            
            //same column
            for (int i = 0; i < dim; i++){
                board.getSquare(col + dim * i).setSafe(false);
                //System.out.print(" " + (col + dim * i));
            }
                
            //same diagonal
            int sqId = id;
            Square sq;
            
            //up, right
            while ((sqId % dim != 0) && (sqId > 8)){
                sq = board.getSquare(sqId -= (dim - 1));
                sq.setSafe(false);
                //System.out.print(" " + sq.getId());
            }
            
            sqId = id;
            //down, right
            while ((sqId % dim != 0) && (sqId < 56)){
                sq = board.getSquare(sqId += (dim + 1));
                sq.setSafe(false);
                //System.out.print(" " + sq.getId());
            }
            
            //up, left
            sqId = id;
            while ((sqId % dim != 1) && (sqId > 9)){
                sq = board.getSquare(sqId -= (dim + 1));
                sq.setSafe(false);
                //System.out.print(" " + sq.getId());
            }
            
            sqId = id;
            //down, left
            while ((sqId % dim != 1) && (sqId < 57)){
                sq = board.getSquare(sqId += (dim - 1));
                sq.setSafe(false);
                //System.out.print(" " + sq.getId());
            }
            
            //System.out.println();
        }
        
        
    }


     private static class ChessboardJFrame extends javax.swing.JFrame {
    
        public static final long serialVersionUID = 1L;
        
        private boolean touring = false;

        /** Creates new form KnightsTourJFrame */
        public ChessboardJFrame() {
            initComponents();
            
            PEightQueen eq = new PEightQueen();
                eq.findSolution(1);
                               
                plotSolution(eq.getSolution());
        }

        /** This method is called from within the constructor to
         * initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
        private void initComponents() {
            jLabel1 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            jLabel7 = new javax.swing.JLabel();
            jLabel8 = new javax.swing.JLabel();
            jLabel9 = new javax.swing.JLabel();
            jLabel10 = new javax.swing.JLabel();
            jLabel11 = new javax.swing.JLabel();
            jLabel12 = new javax.swing.JLabel();
            jLabel13 = new javax.swing.JLabel();
            jLabel14 = new javax.swing.JLabel();
            jLabel15 = new javax.swing.JLabel();
            jLabel16 = new javax.swing.JLabel();
            jLabel17 = new javax.swing.JLabel();
            jLabel18 = new javax.swing.JLabel();
            jLabel19 = new javax.swing.JLabel();
            jLabel20 = new javax.swing.JLabel();
            jLabel21 = new javax.swing.JLabel();
            jLabel22 = new javax.swing.JLabel();
            jLabel23 = new javax.swing.JLabel();
            jLabel24 = new javax.swing.JLabel();
            jLabel25 = new javax.swing.JLabel();
            jLabel26 = new javax.swing.JLabel();
            jLabel27 = new javax.swing.JLabel();
            jLabel28 = new javax.swing.JLabel();
            jLabel29 = new javax.swing.JLabel();
            jLabel30 = new javax.swing.JLabel();
            jLabel31 = new javax.swing.JLabel();
            jLabel32 = new javax.swing.JLabel();
            jLabel33 = new javax.swing.JLabel();
            jLabel34 = new javax.swing.JLabel();
            jLabel35 = new javax.swing.JLabel();
            jLabel36 = new javax.swing.JLabel();
            jLabel37 = new javax.swing.JLabel();
            jLabel38 = new javax.swing.JLabel();
            jLabel39 = new javax.swing.JLabel();
            jLabel40 = new javax.swing.JLabel();
            jLabel41 = new javax.swing.JLabel();
            jLabel42 = new javax.swing.JLabel();
            jLabel43 = new javax.swing.JLabel();
            jLabel44 = new javax.swing.JLabel();
            jLabel45 = new javax.swing.JLabel();
            jLabel46 = new javax.swing.JLabel();
            jLabel47 = new javax.swing.JLabel();
            jLabel48 = new javax.swing.JLabel();
            jLabel49 = new javax.swing.JLabel();
            jLabel50 = new javax.swing.JLabel();
            jLabel51 = new javax.swing.JLabel();
            jLabel52 = new javax.swing.JLabel();
            jLabel53 = new javax.swing.JLabel();
            jLabel54 = new javax.swing.JLabel();
            jLabel55 = new javax.swing.JLabel();
            jLabel56 = new javax.swing.JLabel();
            jLabel57 = new javax.swing.JLabel();
            jLabel58 = new javax.swing.JLabel();
            jLabel59 = new javax.swing.JLabel();
            jLabel60 = new javax.swing.JLabel();
            jLabel61 = new javax.swing.JLabel();
            jLabel62 = new javax.swing.JLabel();
            jLabel63 = new javax.swing.JLabel();
            jLabel64 = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setTitle("Eight Queens");
            setBackground(new java.awt.Color(0, 102, 51));
            setForeground(java.awt.Color.white);
            setResizable(false);
            jLabel1.setBackground(new java.awt.Color(0, 0, 0));
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel1.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel1.setName("1");
            jLabel1.setOpaque(true);
            jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel2.setBackground(new java.awt.Color(255, 255, 255));
            jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel2.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel2.setName("2");
            jLabel2.setOpaque(true);
            jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel3.setBackground(new java.awt.Color(0, 0, 0));
            jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel3.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel3.setName("3");
            jLabel3.setOpaque(true);
            jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel4.setBackground(new java.awt.Color(255, 255, 255));
            jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel4.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel4.setName("4");
            jLabel4.setOpaque(true);
            jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel5.setBackground(new java.awt.Color(0, 0, 0));
            jLabel5.setForeground(new java.awt.Color(255, 255, 255));
            jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel5.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel5.setName("5");
            jLabel5.setOpaque(true);
            jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel6.setBackground(new java.awt.Color(255, 255, 255));
            jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel6.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel6.setName("6");
            jLabel6.setOpaque(true);
            jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel7.setBackground(new java.awt.Color(0, 0, 0));
            jLabel7.setForeground(new java.awt.Color(255, 255, 255));
            jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel7.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel7.setName("7");
            jLabel7.setOpaque(true);
            jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel8.setBackground(new java.awt.Color(255, 255, 255));
            jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel8.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel8.setName("8");
            jLabel8.setOpaque(true);
            jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel9.setBackground(new java.awt.Color(255, 255, 255));
            jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel9.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel9.setName("9");
            jLabel9.setOpaque(true);
            jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel10.setBackground(new java.awt.Color(0, 0, 0));
            jLabel10.setForeground(new java.awt.Color(255, 255, 255));
            jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel10.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel10.setName("10");
            jLabel10.setOpaque(true);
            jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel11.setBackground(new java.awt.Color(255, 255, 255));
            jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel11.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel11.setName("11");
            jLabel11.setOpaque(true);
            jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel12.setBackground(new java.awt.Color(0, 0, 0));
            jLabel12.setForeground(new java.awt.Color(255, 255, 255));
            jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel12.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel12.setName("12");
            jLabel12.setOpaque(true);
            jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel13.setBackground(new java.awt.Color(255, 255, 255));
            jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel13.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel13.setName("13");
            jLabel13.setOpaque(true);
            jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel14.setBackground(new java.awt.Color(0, 0, 0));
            jLabel14.setForeground(new java.awt.Color(255, 255, 255));
            jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel14.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel14.setName("14");
            jLabel14.setOpaque(true);
            jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel15.setBackground(new java.awt.Color(255, 255, 255));
            jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel15.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel15.setName("15");
            jLabel15.setOpaque(true);
            jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel16.setBackground(new java.awt.Color(0, 0, 0));
            jLabel16.setForeground(new java.awt.Color(255, 255, 255));
            jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel16.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel16.setName("16");
            jLabel16.setOpaque(true);
            jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel17.setBackground(new java.awt.Color(0, 0, 0));
            jLabel17.setForeground(new java.awt.Color(255, 255, 255));
            jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel17.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel17.setName("17");
            jLabel17.setOpaque(true);
            jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel18.setBackground(new java.awt.Color(255, 255, 255));
            jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel18.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel18.setName("18");
            jLabel18.setOpaque(true);
            jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel19.setBackground(new java.awt.Color(0, 0, 0));
            jLabel19.setForeground(new java.awt.Color(255, 255, 255));
            jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel19.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel19.setName("19");
            jLabel19.setOpaque(true);
            jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel20.setBackground(new java.awt.Color(255, 255, 255));
            jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel20.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel20.setName("20");
            jLabel20.setOpaque(true);
            jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel21.setBackground(new java.awt.Color(0, 0, 0));
            jLabel21.setForeground(new java.awt.Color(255, 255, 255));
            jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel21.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel21.setName("21");
            jLabel21.setOpaque(true);
            jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel22.setBackground(new java.awt.Color(255, 255, 255));
            jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel22.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel22.setName("22");
            jLabel22.setOpaque(true);
            jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel23.setBackground(new java.awt.Color(0, 0, 0));
            jLabel23.setForeground(new java.awt.Color(255, 255, 255));
            jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel23.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel23.setName("23");
            jLabel23.setOpaque(true);
            jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel24.setBackground(new java.awt.Color(255, 255, 255));
            jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel24.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel24.setName("24");
            jLabel24.setOpaque(true);
            jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel25.setBackground(new java.awt.Color(255, 255, 255));
            jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel25.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel25.setName("25");
            jLabel25.setOpaque(true);
            jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel26.setBackground(new java.awt.Color(0, 0, 0));
            jLabel26.setForeground(new java.awt.Color(255, 255, 255));
            jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel26.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel26.setName("26");
            jLabel26.setOpaque(true);
            jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel27.setBackground(new java.awt.Color(255, 255, 255));
            jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel27.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel27.setName("27");
            jLabel27.setOpaque(true);
            jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel28.setBackground(new java.awt.Color(0, 0, 0));
            jLabel28.setForeground(new java.awt.Color(255, 255, 255));
            jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel28.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel28.setName("28");
            jLabel28.setOpaque(true);
            jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel29.setBackground(new java.awt.Color(255, 255, 255));
            jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel29.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel29.setName("29");
            jLabel29.setOpaque(true);
            jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel30.setBackground(new java.awt.Color(0, 0, 0));
            jLabel30.setForeground(new java.awt.Color(255, 255, 255));
            jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel30.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel30.setName("30");
            jLabel30.setOpaque(true);
            jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel31.setBackground(new java.awt.Color(255, 255, 255));
            jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel31.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel31.setName("31");
            jLabel31.setOpaque(true);
            jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel32.setBackground(new java.awt.Color(0, 0, 0));
            jLabel32.setForeground(new java.awt.Color(255, 255, 255));
            jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel32.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel32.setName("32");
            jLabel32.setOpaque(true);
            jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel33.setBackground(new java.awt.Color(0, 0, 0));
            jLabel33.setForeground(new java.awt.Color(255, 255, 255));
            jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel33.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel33.setName("33");
            jLabel33.setOpaque(true);
            jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel34.setBackground(new java.awt.Color(255, 255, 255));
            jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel34.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel34.setName("34");
            jLabel34.setOpaque(true);
            jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel35.setBackground(new java.awt.Color(0, 0, 0));
            jLabel35.setForeground(new java.awt.Color(255, 255, 255));
            jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel35.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel35.setName("35");
            jLabel35.setOpaque(true);
            jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel36.setBackground(new java.awt.Color(255, 255, 255));
            jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel36.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel36.setName("36");
            jLabel36.setOpaque(true);
            jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel37.setBackground(new java.awt.Color(0, 0, 0));
            jLabel37.setForeground(new java.awt.Color(255, 255, 255));
            jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel37.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel37.setName("37");
            jLabel37.setOpaque(true);
            jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel38.setBackground(new java.awt.Color(255, 255, 255));
            jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel38.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel38.setName("38");
            jLabel38.setOpaque(true);
            jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel39.setBackground(new java.awt.Color(0, 0, 0));
            jLabel39.setForeground(new java.awt.Color(255, 255, 255));
            jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel39.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel39.setName("39");
            jLabel39.setOpaque(true);
            jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel40.setBackground(new java.awt.Color(255, 255, 255));
            jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel40.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel40.setName("40");
            jLabel40.setOpaque(true);
            jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel41.setBackground(new java.awt.Color(255, 255, 255));
            jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel41.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel41.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel41.setName("41");
            jLabel41.setOpaque(true);
            jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel42.setBackground(new java.awt.Color(0, 0, 0));
            jLabel42.setForeground(new java.awt.Color(255, 255, 255));
            jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel42.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel42.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel42.setName("42");
            jLabel42.setOpaque(true);
            jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel43.setBackground(new java.awt.Color(255, 255, 255));
            jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel43.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel43.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel43.setName("43");
            jLabel43.setOpaque(true);
            jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel44.setBackground(new java.awt.Color(0, 0, 0));
            jLabel44.setForeground(new java.awt.Color(255, 255, 255));
            jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel44.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel44.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel44.setName("44");
            jLabel44.setOpaque(true);
            jLabel44.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel45.setBackground(new java.awt.Color(255, 255, 255));
            jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel45.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel45.setName("45");
            jLabel45.setOpaque(true);
            jLabel45.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel46.setBackground(new java.awt.Color(0, 0, 0));
            jLabel46.setForeground(new java.awt.Color(255, 255, 255));
            jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel46.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel46.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel46.setName("46");
            jLabel46.setOpaque(true);
            jLabel46.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel47.setBackground(new java.awt.Color(255, 255, 255));
            jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel47.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel47.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel47.setName("47");
            jLabel47.setOpaque(true);
            jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel48.setBackground(new java.awt.Color(0, 0, 0));
            jLabel48.setForeground(new java.awt.Color(255, 255, 255));
            jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel48.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel48.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel48.setName("48");
            jLabel48.setOpaque(true);
            jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel49.setBackground(new java.awt.Color(0, 0, 0));
            jLabel49.setForeground(new java.awt.Color(255, 255, 255));
            jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel49.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel49.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel49.setName("49");
            jLabel49.setOpaque(true);
            jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel50.setBackground(new java.awt.Color(255, 255, 255));
            jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel50.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel50.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel50.setName("50");
            jLabel50.setOpaque(true);
            jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel51.setBackground(new java.awt.Color(0, 0, 0));
            jLabel51.setForeground(new java.awt.Color(255, 255, 255));
            jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel51.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel51.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel51.setName("51");
            jLabel51.setOpaque(true);
            jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel52.setBackground(new java.awt.Color(255, 255, 255));
            jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel52.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel52.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel52.setName("52");
            jLabel52.setOpaque(true);
            jLabel52.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel53.setBackground(new java.awt.Color(0, 0, 0));
            jLabel53.setForeground(new java.awt.Color(255, 255, 255));
            jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel53.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel53.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel53.setName("53");
            jLabel53.setOpaque(true);
            jLabel53.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel54.setBackground(new java.awt.Color(255, 255, 255));
            jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel54.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel54.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel54.setName("54");
            jLabel54.setOpaque(true);
            jLabel54.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel55.setBackground(new java.awt.Color(0, 0, 0));
            jLabel55.setForeground(new java.awt.Color(255, 255, 255));
            jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel55.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel55.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel55.setName("55");
            jLabel55.setOpaque(true);
            jLabel55.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel56.setBackground(new java.awt.Color(255, 255, 255));
            jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel56.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel56.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel56.setName("56");
            jLabel56.setOpaque(true);
            jLabel56.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel57.setBackground(new java.awt.Color(255, 255, 255));
            jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel57.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel57.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel57.setName("57");
            jLabel57.setOpaque(true);
            jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel58.setBackground(new java.awt.Color(0, 0, 0));
            jLabel58.setForeground(new java.awt.Color(255, 255, 255));
            jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel58.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel58.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel58.setName("58");
            jLabel58.setOpaque(true);
            jLabel58.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel59.setBackground(new java.awt.Color(255, 255, 255));
            jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel59.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel59.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel59.setName("59");
            jLabel59.setOpaque(true);
            jLabel59.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel60.setBackground(new java.awt.Color(0, 0, 0));
            jLabel60.setForeground(new java.awt.Color(255, 255, 255));
            jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel60.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel60.setName("60");
            jLabel60.setOpaque(true);
            jLabel60.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel61.setBackground(new java.awt.Color(255, 255, 255));
            jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel61.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel61.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel61.setName("61");
            jLabel61.setOpaque(true);
            jLabel61.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel62.setBackground(new java.awt.Color(0, 0, 0));
            jLabel62.setForeground(new java.awt.Color(255, 255, 255));
            jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel62.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel62.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel62.setName("62");
            jLabel62.setOpaque(true);
            jLabel62.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel63.setBackground(new java.awt.Color(255, 255, 255));
            jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel63.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel63.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel63.setName("63");
            jLabel63.setOpaque(true);
            jLabel63.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            jLabel64.setBackground(new java.awt.Color(0, 0, 0));
            jLabel64.setForeground(new java.awt.Color(255, 255, 255));
            jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel64.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel64.setMinimumSize(new java.awt.Dimension(30, 30));
            jLabel64.setName("64");
            jLabel64.setOpaque(true);
            jLabel64.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    squareClick(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            pack();
        }// </editor-fold>                        

        private void plotSolution(java.util.HashSet<Integer> squares){

            java.util.Iterator<Integer> it = squares.iterator();
            
            while (it.hasNext()){
                Integer sq = it.next();
                
                java.awt.Component[] comps = this.getContentPane().getComponents();

                for(int c = 0; c < comps.length; c++){

                    if ((comps[c].getName() != null) && 
                        (sq.compareTo(Integer.parseInt(comps[c].getName())) == 0)) {
                        ((JLabel)comps[c]).setText("Q");
                    }

                }

            }

        }



        private void squareClick(java.awt.event.MouseEvent evt) {                             

        }                            

        // Variables declaration - do not modify                     
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel13;
        private javax.swing.JLabel jLabel14;
        private javax.swing.JLabel jLabel15;
        private javax.swing.JLabel jLabel16;
        private javax.swing.JLabel jLabel17;
        private javax.swing.JLabel jLabel18;
        private javax.swing.JLabel jLabel19;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel20;
        private javax.swing.JLabel jLabel21;
        private javax.swing.JLabel jLabel22;
        private javax.swing.JLabel jLabel23;
        private javax.swing.JLabel jLabel24;
        private javax.swing.JLabel jLabel25;
        private javax.swing.JLabel jLabel26;
        private javax.swing.JLabel jLabel27;
        private javax.swing.JLabel jLabel28;
        private javax.swing.JLabel jLabel29;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel30;
        private javax.swing.JLabel jLabel31;
        private javax.swing.JLabel jLabel32;
        private javax.swing.JLabel jLabel33;
        private javax.swing.JLabel jLabel34;
        private javax.swing.JLabel jLabel35;
        private javax.swing.JLabel jLabel36;
        private javax.swing.JLabel jLabel37;
        private javax.swing.JLabel jLabel38;
        private javax.swing.JLabel jLabel39;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel40;
        private javax.swing.JLabel jLabel41;
        private javax.swing.JLabel jLabel42;
        private javax.swing.JLabel jLabel43;
        private javax.swing.JLabel jLabel44;
        private javax.swing.JLabel jLabel45;
        private javax.swing.JLabel jLabel46;
        private javax.swing.JLabel jLabel47;
        private javax.swing.JLabel jLabel48;
        private javax.swing.JLabel jLabel49;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel50;
        private javax.swing.JLabel jLabel51;
        private javax.swing.JLabel jLabel52;
        private javax.swing.JLabel jLabel53;
        private javax.swing.JLabel jLabel54;
        private javax.swing.JLabel jLabel55;
        private javax.swing.JLabel jLabel56;
        private javax.swing.JLabel jLabel57;
        private javax.swing.JLabel jLabel58;
        private javax.swing.JLabel jLabel59;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel60;
        private javax.swing.JLabel jLabel61;
        private javax.swing.JLabel jLabel62;
        private javax.swing.JLabel jLabel63;
        private javax.swing.JLabel jLabel64;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        // End of variables declaration                   

    }

}
