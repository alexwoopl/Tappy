<h1>Semi-boiled down Requirements:</h1>

<h2>NOTE:</h2>
<p>
I used the apache commons library for CSV formatting purposes, as this has a free use license. 
I could have created a couple classes for creating the CSV processing but I didn't feel this added much value and is a problem that has already been solved!
I believe in using libraries instead of solving issues that have already been solved, especially if they're free.
</p>
  
<h2>Spec:</h2>
<p>There are 3 stations;(1, 2 and 3)</p>
<p>You "tap on" at the starting station and "tap off" at the end station.</p>
<p>From station to station has different costs(same in both directions):</p>
<p>1 <=> 2: $3.25</p>
<p>1 <=> 3: $7.30</p>
<p>2 <=> 3: $5.50</p>
<p>NUM <=> SAME NUM: 0</p>


<h2>Input:</h2>
a csv describing a series of tap activities e.g.:
<p>
ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN
1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559
2, 22-01-2018 13:05:00, OFF, Stop2, Company1, Bus37, 5500005555555559
</p>
  
<h2>Output:</h2>
a csv describing total trip info e.g.:<\br>
'''
Started,Finished,DurationSecs,FromStopId,ToStopId,ChargeAmount,CompanyId,BusID,PAN,Status<\br>
22-01-2018 13:00:00,22-01-2018 13:05:00,900,Stop1,Stop2,$3.25,Company1,B37,5500005555555559,COMPLETED<\br>
'''

<h2>Extra info:</h2>
- An incomplete trip (tap on but no tap off) will charge the maximum possible from the tap on station
- Tapping on and off at the same station is a cancelled trip with no charge (time stamp doesn't matter)


<h2>Assumptions:</h2>
- A user will never tap off without tapping on first.
- An input csv is all on the same day (This seem like a daily batch job that would run)
- The input is always in chronological order.
- When tapping on and then off the user's company ID, bus ID and PAN will not change. (i.e. If those 3 match between and ON and OFF tap then it will be one "trip")
- The input file will be of reasonable length (I will not put a length restriction)
- An incomplete ride will output ""<i.e. an empty string> for 'duration', 'Finished' and 'ToStopId'
- All error messages should be printed to console instead of logging
- If a CSV is given it will be correctly formatted and with valid data (content and filename valid and existing).
- The output file is ordered by endTime with INCOMPLETE trips at the end.

<h2>Tests supporting info:</h2>
Link for test card numbers - http://support.worldpay.com/support/kb/bg/testandgolive/tgl5103.html

<h2>How to run:</h2>
*You will need Java 8*
- JAR: Download the Jar file, then run this command: java -jar Tappy-1.0.jar <Input filepath>

<h2>How to run tests:</h2>
*You will need Java 8 and Maven*
- For automation: Clone the repo then go to repo root and run this command: mvn clean test
- For manual running: open this in intellij and run the tests via IDE
