import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SRTF {

    static CPU cpu=new CPU();

    private static int NumOfP=0;
//    private static int Quantum=0;

    static Process[] Processes ;
    static Queue<Process> ReadyQue = new LinkedList<Process>();
    static Queue<Process> completed = new LinkedList<Process>();
    static int ContextSwitching;
    //    static int RQ_size=-1;


    public static void main(String[] args) {

        TakeInputs();
        OrderFirstCome();

        int Time=0;
        int Co_Sw=0;
        while (completed.size()!=NumOfP)
        {
            ReceiveAt(Time);

            //context switching
            if(ReadyQue.size()!=0)
            if(ReadyQue.peek().Burst<cpu.CurrentP.Burst && cpu.CurrentP.Burst>0)
            {
                Co_Sw=ContextSwitching;
                while (Co_Sw!=0)
                {
                    Co_Sw--;
                    IncreaseWaitedTime();
                    Time++;
                    ReceiveAt(Time);
                }
                InsertInPlace_RQ(cpu.CurrentP);
                //what if during switching peek changed
                cpu.CurrentP=ReadyQue.poll();
            }

            //first or after empty time
            if(cpu.CurrentP.Burst<0)
            {
                if(ReadyQue.size()!=0)
                {
                    cpu.CurrentP=ReadyQue.poll();
                }
            }

            //completed
            if(cpu.CurrentP.Burst==0)
            {
                AddToCompletedQue(cpu.CurrentP,Time);
                if(ReadyQue.size()==0)cpu.CurrentP=new Process();
                else cpu.CurrentP=ReadyQue.poll();
            }

            //running
            if(cpu.CurrentP.Burst>0)
            {
                cpu.CurrentP.Burst--;
                IncreaseWaitedTime();
            }

            Time++;
        }



        for (Process p:completed) {
            System.out.println(p.name);
        }

        Statistics();

    }


    static void AddToCompletedQue(Process p,int T)
    {
        completed.offer(p);
        p.Completion=T;
        p.setTurnaround();
    }


    static void ReceiveAt(int arrival_T)
    {
        for (int i = 0; i <NumOfP ; i++) {
            if(Processes[i].Arrival==arrival_T)
            {
                InsertInPlace_RQ(Processes[i]);
            }
        }
    }

    static void InsertInPlace_RQ(Process p)
    {
        if(ReadyQue.size()==0){ReadyQue.offer(p); return;
        }

        Process temp;
        boolean inserted=false;
        int n=ReadyQue.size();

        for (int i=0;i<n;i++) {
            temp=ReadyQue.poll();
            if(p.Burst<temp.Burst&&!inserted)
            {
                inserted=true;
                ReadyQue.offer(p);
            }
            ReadyQue.offer(temp);
        }
        if(!inserted) {
            ReadyQue.offer(p);
        }

    }

    private static void OrderFirstCome() {

        for (int i = 0; i <NumOfP-1 ; i++) {
            for (int j = 0; j <NumOfP-1 ; j++) {

                if(Processes[j].Arrival>Processes[j+1].Arrival)
                {
                    Process temp=Processes[j];
                    Processes[j]=Processes[j+1];
                    Processes[j+1]=temp;
                }
            }
        }
    }


    static void TakeInputs()
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter number of Processes: ");
        NumOfP = scan.nextInt();
        Initialize();

//        System.out.println("Quantum Time: ");
//        Quantum= scan.nextInt();

        System.out.println("Context Switching: ");
        ContextSwitching=scan.nextInt();


        scan.nextLine();
        System.out.println("Enter processes names: ");
        for (int i = 0; i < NumOfP; i++) {
//            System.out.println("Enter process " + (i + 1) + " name: ");
            Processes[i].name = scan.next();
        }

        System.out.println("Enter processes Arrival times: ");
        for (int i = 0; i < NumOfP; i++) {
//            System.out.println("Enter process " + (i + 1) + " Arrival time: ");
            Processes[i].Arrival = scan.nextInt();
        }

        System.out.println("Enter processes  Burst times: ");
        for (int i = 0; i < NumOfP; i++) {
//            System.out.println("Enter process " + (i + 1) + " Burst time: ");
            Processes[i].Burst = scan.nextInt();
        }

//      for (int i = 0; i <NumOfP; i++) {
//          System.out.println("Enter process " + (i + 1) + " priority: ");
//          Processes[i].Priority = scan.nextInt();
//       }

    }


    static void Initialize()
    {
        Processes = new Process[NumOfP];
        for (int i = 0; i < NumOfP ; i++) {
            Processes[i]=new Process();
        }
    }



    static void Statistics()
    {
        for (Process p:completed) {
            System.out.print(p.name+" Waited for "+p.Waited);
            System.out.println(" with turnaround time of "+p.Turnaround);
        }
        System.out.println("Average Waiting Time: ");
        System.out.println(" ="+AverageWaitingTime());
        System.out.println("Average Turnaround Time: ");
        System.out.println(" ="+AverageTurnaround());
    }

    static float AverageTurnaround()
    {
        float sum=0;
        for (Process p:completed) {
//            System.out.print(p.Turnaround+" + ");
            sum+=p.Turnaround;
        }
//        System.out.println("/"+NumOfP);
        return (float)sum/NumOfP;
    }

    static float AverageWaitingTime()
    {
        float sum=0;
        for (Process p:completed) {
//            System.out.print(p.Waited+" + ");
            sum+=p.Waited;
        }
//        System.out.println("/"+NumOfP);
        return (float)sum/NumOfP;
    }

    static void IncreaseWaitedTime()
    {
        int n=ReadyQue.size();
        for (Process p:ReadyQue) {
            p.Waited++;
        }
    }


}

