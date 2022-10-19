/* Custom Signal Handlers
Creates custom signal handlers
Author: Diyaa Nassar- nassard@bc.edu
*/
#include <errno.h>
#include <math.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define NUM_HANDLERS 5
#define MAX_COUNT 5

typedef void (*sighandler_t)(int);

sighandler_t custom[NUM_HANDLERS];
sighandler_t def[NUM_HANDLERS];

sighandler_t sigaction_checked(int sig_num, sighandler_t handler) {
    struct sigaction new_action;
    struct sigaction old_action;
    new_action.sa_handler = handler;
    sigaction(sig_num, &new_action, &old_action);
    sigemptyset(&(new_action.sa_mask));
    sigaddset(&(new_action.sa_mask), sig_num);
    if (errno != 0) {
        fprintf(stderr, "Error installing signal handler: %s\n", strerror(errno));
        exit(EXIT_FAILURE);
    }
    else {
        return(old_action.sa_handler);
    }
}

void sighup(int sig_num) {
    write(STDOUT_FILENO, "Don't interrupt me!\n", 19);
}
void sigint(int sig_num){ 
    write(STDOUT_FILENO, "Don't hang up on me!\n", 20);
}
void sigquit(int sig_num) {
    write(STDOUT_FILENO,"I refuse to quit!\n", 17);
}
void sigill(int sig_num) {
    write(STDOUT_FILENO, "I did nothing illegal!\n", 22);
}

static int count = 0;
void custom_handler(int sig_num) {
    count++;
    if (sig_num == 1) {
        signal(SIGHUP, sighup);
    }
    if (sig_num == 2) {
        signal(SIGINT, sigint);
        
    }
    if (sig_num == 3) {
        signal(SIGQUIT, sigquit);
    }
    if (sig_num == 4) {
        signal(SIGILL, sigkill);
    }
    if (count >= MAX_COUNT) {
        sigaction_checked(sig_num, custom_handler);
    }
}

void install_handlers() {
    custom[1] = signal(SIGHUP, sighup);
    custom[2] = signal(SIGINT, sigint);
    custom[3] = signal(SIGQUIT, sigquit);
    custom[4] = signal(SIGILL, sigikll);
    for (int i = 1; i<=4; i++){
        sigaction_checked(i, custom_handler);
    }
}

int main() {
    printf("PID: %d\n", getpid());
    install_handlers();
    while (1) {
    }
 return EXIT_SUCCESS;
}
