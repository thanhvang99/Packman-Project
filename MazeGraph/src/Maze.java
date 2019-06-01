import edu.princeton.cs.algs4.In;

public class Maze {
    // 1 --> Wall, 0 --> Normal, 2 --> Moved
    private int[][] array;
    private int row;
    private int column;
    public Maze(In in){
        column = in.readInt();
        row = in.readInt();
        array = new int[row][column];
        buildMaze(in);
    }
    private void buildMaze(In in){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                array[i][j] = in.readInt();
            }
        }
    }

    public int convertToIndex1D(int currentRow,int currentColumn){
        return currentRow*column + currentColumn;
    }
    public int getRowFromIndex1D(int index){return (int) Math.floor(index/column);}
    public int getColumnFromIndex1D(int index){return (index-(int)(Math.floor(index/column)*column));}

    public void display(){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                System.out.print(array[i][j]+ " ");
            }
            System.out.println();
        }

    }
    public int getRow(){return row;}
    public int getColumn(){return column;}
    public int[][] getMap(){return array;}
    public int valueAt(int row,int column){return array[row][column];}
    public void updateValueAt(int row,int column,int value){
        array[row][column] = value;

    }


}
