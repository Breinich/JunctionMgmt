// Agent controller in project 

/* Initial beliefs and rules */

// waiting weight and waiting count
bid(0,0)[source(trafficLight1)].
bid(0,0)[source(trafficLight2)].
bid(0,0)[source(trafficLight3)].
bid(0,0)[source(trafficLight4)].

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello main.world.").

+!updateBid(W,C)[source(S)] : true <-
    .print("bid: ",W,C,S);
    .abolish(bid(_,_)[source(S)]);
    +bid(W,C)[source(S)].