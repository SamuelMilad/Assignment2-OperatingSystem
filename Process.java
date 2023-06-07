public class Process implements Comparable<Process>{

    Process()
    {
        name="";
        Waited=0;
        Turnaround=0;

        Arrival=-1;
        Burst=-1;
        Completion=-1;


        starving=0;

    }

    public  String name;
    public  int Arrival;
    public  int Burst;
    public  int Completion;
    public  int Turnaround;
    public  int Waited;
    public  int Priority;
    public  int starving;
    public  int Quantum;


    public String processName;
	public int arrivalTime;
	public int totalWaiting = 0;
	public int arrival_Time;
	public int Burst_time;
	public int burstTime;
	public int priority;
	public int totalWait = 0;
	public int quauntum;

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getTotalWaiting() {
		return totalWaiting;
	}

	public void setTotalWaiting(int totalWaiting) {
		this.totalWaiting = totalWaiting;
	}

	public int getArrival_Time() {
		return arrival_Time;
	}

	public void setArrival_Time(int arrival_Time) {
		this.arrival_Time = arrival_Time;
	}

	public int getBurst_time() {
		return Burst_time;
	}

	public void setBurst_time(int burst_time) {
		Burst_time = burst_time;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getTotalWait() {
		return totalWait;
	}

	public void setTotalWait(int totalWait) {
		this.totalWait = totalWait;
	}

	public int getQuauntum() {
		return quauntum;
	}

	public void setQuauntum(int quauntum) {
		this.quauntum = quauntum;
	}

	Process(String _name, int _burstTime, int _arrivalTime, int _priority, int _q) {
		processName = _name;
		arrivalTime = _arrivalTime;
		Burst_time = _burstTime;
		burstTime = _burstTime;
		priority = _priority;
		arrival_Time = _arrivalTime;
		this.quauntum = _q;
	}

//    public  String state;

    public void setTurnaround() {
        Turnaround = Completion-Arrival;
    }

    
   // int arrivalTime;
    int curBurstTime;
    //int burstTime;
    int exitTime;
    int turnAroundTime;
    int waitingTime;
    int queueNumber;
    //int priority;

    //public int getArrivalTime() {
      //  return this.arrivalTime;
    //}

    @Override
    public int compareTo(Process o) {
        {
            if(this.arrivalTime < o.arrivalTime){
                return  1;
            }
            else if(this.arrivalTime == o.arrivalTime)
            {
                return  0;
            }
            else{
                return -1;
            }
        }
    }



    
}
