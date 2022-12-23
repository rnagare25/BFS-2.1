class Solution {
    /*
    Time complexity:o(m*n) as we are visting each and every element in the grid
    Space complexity:o(m*n) there could be case where all the oranges are rotten then queue will have all the elements from the grid.
    */
    public int orangesRotting(int[][] grid) {
        //1 is fresh  and 2 is rotten
        //Using level order traversal here because
        //already rotten tomatoes will be at first level and its neigbours will be at second level, neigbours neighbours will be at 3 rd level. The idea is process all the elemenst belonging to same level together
        
      /*  1. Put all the rotten oranges in a queue
        2. take them out of the queue and visit neigbours and make them as a rotten if its fresh and add it in the queue.
        */
        if(grid == null || grid.length ==0) return 0;
        int rows= grid.length;
        int cols =grid[0].length;
        Queue<int[]> queue =new LinkedList<>();
        int time =0;
        int[][] dirs={{0,-1},{1,0},{-1,0},{0,1}};
        
        
        //put rotten oranges into queue and count fresh oranges
        int freshCount=0;
        for(int row=0;row<rows;row++){
            for(int col=0;col<cols;col++){
                if(grid[row][col]==2){
                    queue.add(new int[]{row,col});
                } else if(grid[row][col]==1){
                    freshCount++;
                }
            }
        }
         
        if(freshCount ==0) return 0;
           
        //take  rotten orange out of the queue one by one and check its neigbours
        
        while(!queue.isEmpty()){
            //size is for keep record of how many elemnts need to process at a particular level
            int size =queue.size();     
            for(int i=0;i<size;i++){
                int [] element =queue.remove();
                //visit elements 4 neigbours 
                for(int dir[]: dirs){
                    int neigbourRow =element[0]+dir[0];
                    int neigbourCol= element[1]+dir[1];
                    
                    if(neigbourRow>=0 && neigbourRow<rows && neigbourCol>=0 && neigbourCol<cols){
                    //if neigbour is fresh
                    if(grid[neigbourRow][neigbourCol] ==1){
                        //make it rotten and add in the queue
                        freshCount--;
                        grid[neigbourRow][neigbourCol]=2;
                        queue.add(new int[]{neigbourRow, neigbourCol});
                    }
                }
                }
                
            }
            //increse the time once we processed one level of tomatoes
            time++;
            
        }
            
        //if we made all the tomatoes rotten then return time else return -1;
        int result = freshCount==0? time-1 : -1;
        return result;
            
    }
}
