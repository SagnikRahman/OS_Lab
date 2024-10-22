import java.util.Scanner;

class Process {
    String name;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int waitingTime;
    int turnaroundTime;

    public Process(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class FCFS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        Process[] processes = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter process name, arrival time, and burst time: ");
            String name = scanner.next();
            int arrivalTime = scanner.nextInt();
            int burstTime = scanner.nextInt();
            processes[i] = new Process(name, arrivalTime, burstTime);
        }

        calculateCompletionTimes(processes);
        calculateWaitingAndTurnaroundTimes(processes);
        displayResults(processes);
        
        scanner.close();
    }

    private static void calculateCompletionTimes(Process[] processes) {
        int time = 0;
        for (Process process : processes) {
            if (time < process.arrivalTime) {
                time = process.arrivalTime;
            }
            process.completionTime = time + process.burstTime;
            time = process.completionTime;
        }
    }

    private static void calculateWaitingAndTurnaroundTimes(Process[] processes) {
        for (Process process : processes) {
            process.turnaroundTime = process.completionTime - process.arrivalTime;
            process.waitingTime = process.turnaroundTime - process.burstTime;
        }
    }

    private static void displayResults(Process[] processes) {
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", "Process", "Arrival Time", "Burst Time", "Completion Time", "Waiting Time", "Turnaround Time");
        
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (Process process : processes) {
            System.out.printf("%-15s %-15d %-15d %-15d %-15d %-15d\n", process.name, process.arrivalTime, process.burstTime, process.completionTime, process.waitingTime, process.turnaroundTime);
            totalWaitingTime += process.waitingTime;
            totalTurnaroundTime += process.turnaroundTime;
        }

        double avgWaitingTime = (double) totalWaitingTime / processes.length;
        double avgTurnaroundTime = (double) totalTurnaroundTime / processes.length;

        System.out.printf("Average Waiting Time: %.2f\n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
    }
}
