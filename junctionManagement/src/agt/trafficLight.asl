// Agent trafficLight in project 

/* Initial beliefs and rules */

waitingSum(0).
waitingCount(0).

/* Initial goals */

!start.

/* Plans */

+!start : .my_name(N) <- .print("hello world, I am " + N).

+newVehicle(weight) : true <-
    .print("new vehicle arrived.");
    !updateWaitingSum(weight, 1).

+vehicleLeft(weight) : true <-
    .print("a vehicle left.");
    !updateWaitingSum(-weight,-1).

+!updateWaitingSum(weight,count) : waitingSum(X) & waitingCount(Y) <-
    X1 is X + weight;
    -waitingSum(X);
    +waitingSum(X1);
    Y1 is Y + count;
    -waitingCount(Y);
    +waitingCount(Y1);
    .print("waiting sum is: "+X1+" and waiting count is: "+Y1);
    !placeBid.

+!placeBid : waitingSum(X) & waitingCount(Y) <-
    .print("placing bid");
    .send(controller, achieve, updateBid(X, Y)).
