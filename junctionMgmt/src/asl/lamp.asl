// Agent lamp in project junctionMgmt

/* Initial beliefs and rules */

color("red").

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").
