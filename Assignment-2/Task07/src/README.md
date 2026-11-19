# CSA09 – Java Programming Assignment 2



## Project Overview
This repository contains Java programs demonstrating the practical application of Java Collections, String handling, Generics, Comparators, and performance benchmarking.

## Task List

| Task | Topic | Program |
|---|---|---|
| Task 01 | String, StringBuilder and StringBuffer | StringBenchmark.java |
| Task 02 | ArrayList, LinkedList and ArrayDeque | PlaylistBenchmark.java |
| Task 03 | HashSet and hashCode–equals | Attendance.java |
| Task 04 | HashMap, TreeMap and LinkedHashMap | WordFrequency.java |
| Task 05 | Generics, Comparable and Comparator | StudentSorting.java |
| Task 06 | Queue and PriorityQueue | ServiceQueue.java |
| Task 07 | Diagnosis, redesign and validation | RedesignBenchmark.java |

## Folder Structure

```text
CSA09-192424002-Assignment2/
│
├── Task01/
│   ├── src/
│   └── evidence/
├── Task02/
│   ├── src/
│   └── evidence/
├── Task03/
│   ├── src/
│   └── evidence/
├── Task04/
│   ├── src/
│   └── evidence/
├── Task05/
│   ├── src/
│   └── evidence/
├── Task06/
│   ├── src/
│   └── evidence/
├── Task07/
│   ├── src/
│   └── evidence/
├── data/
├── README.md
└── Assignment_Report.md
```

## How to Compile and Run

Open PowerShell in the assignment root directory.

Example for Task 01:

```powershell
javac -d Task01\src Task01\src\StringBenchmark.java
java -cp Task01\src StringBenchmark
```

Replace the task number and class name to execute other programs.

## Experimental Work
The programs include collection comparisons, sorting experiments, duplicate-handling tests, and execution-time measurements.

Task 07 compares repeated String concatenation with StringBuilder using average execution times over five runs.

## Tools Used
- Java Development Kit 25
- Visual Studio Code
- Windows PowerShell
- Java Collections Framework

## Author
**Thamarai V**  
Register Number: **192424002**