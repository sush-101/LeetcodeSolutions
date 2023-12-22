class MyCalendar {
    TreeMap<Integer, Integer> bookings;
    public MyCalendar() {
        bookings = new TreeMap();
    }
    
    public boolean book(int start, int end) {
        // if(bookings.size() == 0){
        //     bookings.put(start, end);
        //     return true;
        // }
        int floor = bookings.floorKey(start) == null ? -1 : bookings.floorKey(start);
        int ceil = bookings.ceilingKey(start) == null ? -1 : bookings.ceilingKey(start);
        if(( floor == -1 || start >= bookings.get(floor)) && (ceil == -1 || end <= ceil)){
            bookings.put(start, end);
            return true;
        }
        return false;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */