class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> al = new ArrayList();
        int start = newInterval[0], end = newInterval[1];
        int i = 0;
        while(i < intervals.length && intervals[i][1] < start){
            int toAdd[] = {intervals[i][0], intervals[i][1]};
            al.add(toAdd);
            i++;
        }
        int min = start, max = end;
        while(i < intervals.length && intervals[i][0] <= end){
            min = Math.min(min, intervals[i][0]);
            max = Math.max(max, intervals[i][1]);
            i++;
        }
        al.add(new int[]{min, max});
        while(i < intervals.length){
            int toAdd[] = {intervals[i][0], intervals[i][1]};
            al.add(toAdd);
            i++;
        }
        int res[][] = new int[al.size()][2];
        for(int k=0;k<al.size();k++){
            res[k][0] = al.get(k)[0];
            res[k][1] = al.get(k)[1];
        }
        return res;
    }
}