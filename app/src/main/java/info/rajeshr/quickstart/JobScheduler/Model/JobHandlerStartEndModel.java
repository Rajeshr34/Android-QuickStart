package info.rajeshr.quickstart.JobScheduler.Model;

/**
 * Created by Rajesh on 29/06/2017.
 */

public class JobHandlerStartEndModel {
    private final int start;
    private final int end;
    private final int nextId;
    private int nextErrorID;

    public JobHandlerStartEndModel(int start, int end, int nextId, int nextErrorID) {
        this.start = start;
        this.end = end;
        this.nextId = nextId;
        this.nextErrorID = nextErrorID;
    }

    public int getStart() {
        return start;
    }

    public int getNextId() {
        return nextId;
    }

    public int getEnd() {
        return end;
    }

    public int getNextErrorID() {
        return nextErrorID;
    }

    public void setNextErrorID(int nextErrorID) {
        this.nextErrorID = nextErrorID;
    }
}
