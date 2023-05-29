// Agent controller in project 

/* Initial beliefs and rules */

// waiting weight and waiting count
value(0,0)[source(trafficLight1)].
value(0,0)[source(trafficLight2)].
value(0,0)[source(trafficLight3)].
value(0,0)[source(trafficLight4)].

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello main.world.").

+!updateValue(W,C)[source(S)] : value(W1,C1)[source(X)] & value(W2,C2)[source(Y)] & value(W3,C3)[source(Z)] & not (.my_name(S) == .my_name(X)) & not (.my_name(S) == .my_name(Y)) & not (.my_name(S) == .my_name(Z)) & not (.my_name(Y) == .my_name(X)) & not (.my_name(Z) == .my_name(X)) & not (.my_name(Y) == .my_name(Z)) <-
    .print("new value: ",W,", ",C," from: ",S);
    -value(_,_)[source(S)];
    +value(W,C)[source(S)];
    optimizeLamps(W1,C1,X,W2,C2,Y,W3,C3,Z,W,C,S).