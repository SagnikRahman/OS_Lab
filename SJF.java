import java.util.*;
class Process {
 String name;
 int arrivalTime;
 int burstTime;
 int completionTime;
 int waitingTime;
 int turnaroundTime;
 boolean isCompleted;
 public Process(String name, int arrivalTime, int burstTime) {
 this.name = name;
 this.arrivalTime = arrivalTime;
 this.burstTime = burstTime;
 this.isCompleted = false;
 }
}
public class SJF {
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 System.out.print("Enter the number of processes: ");
 int n = sc.nextInt();
 List<Process> processes = new ArrayList<>();
 for (int i = 0; i < n; i++) {
 System.out.print("Enter process name: ");
 String name = sc.next();
 System.out.print("Enter arrival time for " + name + ": ");
 int arrivalTime = sc.nextInt();
 System.out.print("Enter burst time for " + name + ": ");
 int burstTime = sc.nextInt();
 processes.add(new Process(name, arrivalTime, burstTime));
 }
 int currentTime = 0;
 int totalTAT = 0; 
 int totalWT = 0; 
 int completed = 0;
 while (completed != n) {
 Process shortestJob = null;
 for (Process p : processes) {
 if (p.arrivalTime <= currentTime && !p.isCompleted) {
 if (shortestJob == null || p.burstTime < 
shortestJob.burstTime) {
 shortestJob = p;
 }
 }
 }
 if (shortestJob == null) {
 currentTime++;
 } else {
 shortestJob.completionTime = currentTime + 
shortestJob.burstTime;
 shortestJob.turnaroundTime = shortestJob.completionTime -
shortestJob.arrivalTime;
 shortestJob.waitingTime = shortestJob.turnaroundTime -
shortestJob.burstTime;
 shortestJob.isCompleted = true;
 completed++;
 totalTAT += shortestJob.turnaroundTime;
 totalWT += shortestJob.waitingTime;
 currentTime = shortestJob.completionTime;
 }
 }
 double avgTAT = (double) totalTAT / n;
 double avgWT = (double) totalWT / n;
 
System.out.println("\nProcess\tArrival\tBurst\tCompletion\tTurnaround\tWaiting")
;
 for (Process p : processes) {
 System.out.println(p.name + "\t" + p.arrivalTime + "\t" + 
p.burstTime + "\t" + p.completionTime + "\t\t" + p.turnaroundTime + "\t\t" + 
p.waitingTime);
 }
 System.out.printf("\nAverage Turnaround Time: %.2f\n", avgTAT);
 System.out.printf("Average Waiting Time: %.2f\n", avgWT);
 sc.close();
 }
}
