#include <stdio.h>
#include <stdbool.h>

struct Process {
    char name[10];
    int arrivalTime;
    int burstTime;
    int completionTime;
    int waitingTime;
    int turnaroundTime;
    bool isCompleted;
};

int main() {
    int n, currentTime = 0, completed = 0, totalTAT = 0, totalWT = 0;
    double avgTAT, avgWT;

    printf("Enter the number of processes: ");
    scanf("%d", &n);

    struct Process processes[n];

    // Input process details
    for (int i = 0; i < n; i++) {
        printf("Enter process name: ");
        scanf("%s", processes[i].name);
        printf("Enter arrival time for %s: ", processes[i].name);
        scanf("%d", &processes[i].arrivalTime);
        printf("Enter burst time for %s: ", processes[i].name);
        scanf("%d", &processes[i].burstTime);
        processes[i].isCompleted = false;
    }

    // SJF scheduling
    while (completed != n) {
        struct Process *shortestJob = NULL;

        for (int i = 0; i < n; i++) {
            if (processes[i].arrivalTime <= currentTime && !processes[i].isCompleted) {
                if (shortestJob == NULL || processes[i].burstTime < shortestJob->burstTime) {
                    shortestJob = &processes[i];
                }
            }
        }

        if (shortestJob == NULL) {
            currentTime++;
        } else {
            shortestJob->completionTime = currentTime + shortestJob->burstTime;
            shortestJob->turnaroundTime = shortestJob->completionTime - shortestJob->arrivalTime;
            shortestJob->waitingTime = shortestJob->turnaroundTime - shortestJob->burstTime;
            shortestJob->isCompleted = true;
            completed++;
            totalTAT += shortestJob->turnaroundTime;
            totalWT += shortestJob->waitingTime;
            currentTime = shortestJob->completionTime;
        }
    }

    // Calculate average TAT and WT
    avgTAT = (double) totalTAT / n;
    avgWT = (double) totalWT / n;

    // Output the results
    printf("\n%-10s%-10s%-10s%-15s%-15s%-10s\n", "Process", "Arrival", "Burst", "Completion", "Turnaround", "Waiting");
    for (int i = 0; i < n; i++) {
        printf("%-10s%-10d%-10d%-15d%-15d%-10d\n", processes[i].name, processes[i].arrivalTime, processes[i].burstTime, processes[i].completionTime, processes[i].turnaroundTime, processes[i].waitingTime);
    }

    printf("\nAverage Turnaround Time: %.2f\n", avgTAT);
    printf("Average Waiting Time: %.2f\n", avgWT);

    return 0;
}
