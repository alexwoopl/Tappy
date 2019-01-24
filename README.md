Semi-boiled down Requirements:

Spec:
There are 3 stations;(1, 2 and 3)
You "tap on" at the starting station and "tap off" at the end station.
From station to station has different costs(same in both directions):
1 <=> 2: 3.25
1 <=> 3: 7.30
2 <=> 3: 5.50
NUM <=> SAME NUM: 0

Input:
a csv describing a series of tap activities e.g.:
'''
ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN
1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559
2, 22-01-2018 13:05:00, OFF, Stop2, Company1, Bus37, 5500005555555559
'''

Output:
a csv describing total trip info e.g.:
'''
Started, Finished, DurationSecs, FromStopId, ToStopId, ChargeAmount, CompanyId, BusID, PAN, Status
22-01-2018 13:00:00, 22-01-2018 13:05:00, 900, Stop1, Stop2, $3.25, Company1, B37, 5500005555555559, COMPLETED
'''

Extra info:
- An incomplete trip (tap on but no tap off) will charge the maximum possible from the tap on station
- Tapping on and off at the same station is a cancelled trip with no charge (time stamp doesn't matter)


Assumptions:
- A user cannot tap off without tapping on.
- If a user taps on while already "tapped on" to another ride then their first ride is considered "incomplete"(ignoring timestamp)
- When tapping on and then off the user's company ID and bus ID will not change.
- The input contains complete information (that is if a user taps on in the input but not off we assume an incomplete ride and another input file wont have 'redemming' information)
- The input file will be of reasonable length (I will not put a length restriction)
- An incomplete ride will output "N/A" for 'duration' and 'ToStopId'
- Junit will be good for the unit testing + end to end testing(usually cucumber or something similar would be used but in this simple case this should suffice nicely).

Tests supporting info:
Link for test card numbers - http://support.worldpay.com/support/kb/bg/testandgolive/tgl5103.html

How to run:
- JAR:

- Clone Repo

How to run tests:
- Clone Repo

- Test Harness