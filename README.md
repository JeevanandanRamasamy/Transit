# Transit

Programming Assignment 2 for the Data Structures course  
[Assignment Link](https://ds.cs.rutgers.edu/assignment-transportation/)

## Problem Description

You’ve just moved into a new city and need to plan your daily commute across three modes of transportation: subway, bus, and walking. The city can be visualized as a number line, with your starting point at position 0. The goal is to determine the most efficient way to reach a destination by using any combination of subway, bus, or walking.

Key considerations:
- The subway is the fastest mode of transportation but can only stop at subway stations.
- The bus is slower than the subway but faster than walking and can stop at bus stops.
- Walking can be done at any bus stop and allows you to walk forward to any point on the number line.

## Approach & Solution

The problem is solved by modeling the transportation options using a **3-layered linked-list** data structure. Each layer represents a mode of transportation (subway, bus, and walking), and the linked list is used to find the optimal path to the destination by traversing through the available stops.

- The 3-layered linked list organizes transportation modes at each stop along the number line.
- The optimal path is determined by choosing the fastest transportation option at each point on the journey.

This approach allows for an efficient computation of the fastest path to the destination, considering the constraints of available transportation modes.

## Key Features

- **3-layered linked-list** for organizing transportation options at each stop.
- Efficient pathfinding to determine the quickest route from the start to the destination.
- Models a city with subway stations, bus stops, and walkable paths.
