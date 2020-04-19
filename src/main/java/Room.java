import java.util.ArrayList;
import java.util.List;

public class Room {
    private int vidth;
    private int length;
    private int size;
    private int boxNum;
    private int seq= 0;
    private int scans= 0;
    private List<Place> space;

    public Room(int vidth, int length, int boxNum) {
        this.vidth = vidth;
        this.length = length;
        this.size = vidth * length;
        this.boxNum = boxNum;
        initSpace();
    }

    public void initSpace() {
        space = new ArrayList<Place>(size);
        for(int i = 0; i < size; i++) {
            space.add(i, new Place());
        }
    }

    public void iterate(int pos, int boxCount) {
        String alarm = "";
        if(boxNum < boxCount )
            return; // alarm = " overfill ";
        if(size - pos + boxCount < boxNum)
            return; // alarm = " shortage ";
        if(pos == size) {
            scans++;
            //if(boxCount == boxNum)
                System.out.println("" + scans + ": boxes=" + boxCount + " " + shovRoom() + " " + ((boxCount == boxNum)? " ! "  + (++seq): "") + alarm);

        } else {
                space.get(pos).isBox = false;
                iterate(pos + 1, boxCount);
                space.get(pos).isBox = true;
                iterate(pos + 1, boxCount + 1);
        }
    }

    public void investigate() {
        seq++;
        //System.out.println(shovRoom(Integer.MAX_VALUE));
    }

    public String shovRoom() {
        StringBuffer sb = new StringBuffer();
        //sb.append(seq);
        //sb.append(": ");
        for (int i = 0; i < size ; i++) {
             sb.append( space.get(i).isBox? "*" : "-");
        }
        return sb.toString();
    }
}
