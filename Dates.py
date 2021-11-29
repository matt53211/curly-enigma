#Matthew Moaut 
# 7552560
import re,sys

  
pattern =  "^(0?[1-9]|[1-2][0-9]|3[0-1])$"
#patternMonth = "^(0?[1-9]|1[0-2])$"
patternYear ="^([1-2][0-9][0-9][0-9]|3000)$"
splitPattern = "-|,|\/| "
splitterArray = ["-",",","/"," "] 
Months = ["Jan","Feb","Mar","Apr","May","Jun"
        ,"Jul","Aug","Sep","Oct","Nov","Dec"]

MonthsL = ["jan","feb","mar","apr","may","jun"
        ,"jul","aug","sep","oct","nov","dec"]

MonthsU = ["JAN","FEB","MAR","APR","MAY","JUN"
        ,"JUL","AUG","SEP","OCT","NOV","DEC"]        

DayMax =  ["31","28","31","30","31","30","31",
                    "31","30","31","30","31"]

#print("please enter the date:")
for line in sys.stdin:
    line = line.strip('\n')
    validDate = True
    poorFormatting = False
    divider = None
    breaker = None
    while validDate:
        inputRaw = line
        count = 0
        #for letter in inputRaw:
        for element in inputRaw:
            if divider is None and element in splitterArray:
                divider = element
                break
        if divider is not None:
            for element in inputRaw:
                if element == divider:
                    count = count + 1
                elif re.match(splitPattern,element) is not None:
                    print(inputRaw + " - INVALID: only one type of divider allowed")
                    validDate = False
                    breaker = True
                    break      
            if breaker is not None:
                break    
        if count > 2:
            print(inputRaw +" - INVALID: input too many dividers")
            validDate = False
            break
        
        input = re.split(splitPattern,inputRaw,maxsplit = 2)
        dayRaw = 0
        monthRaw = 0
        yearRaw = 0
        try:
            dayRaw = input[0]
            if not dayRaw.isnumeric():
                print(inputRaw + " - INVALID: incorrect input type for day")
                validDate = False
                break
                
            elif re.match(pattern, dayRaw) is None:
                print(inputRaw + " - INVALID: day out of range")
                validDate = False
                break
    
            monthRaw = input[1]
            month = monthRaw
            i = 0
            j = 0
            validMonth = False

            if monthRaw.isnumeric() and 0 < int(monthRaw) < 13:
                if len(monthRaw)>2:
                    print(inputRaw + " - INVALID: month input too long")
                    validMonth = False
                    break
                #if re.search(patternMonth, monthRaw) is not None:
                month = Months[(int(monthRaw)-1)]
                validMonth = True
                j = int(monthRaw)
            
            elif  monthRaw.lower().isalpha():
                if len(monthRaw)>3:
                    print(inputRaw + " - INVALID: month input too long")
                    validMonth = False
                    break
                for i in range(len(Months)):
                    if monthRaw == Months[i] or monthRaw == MonthsL[i] or monthRaw == MonthsU[i]:
                        month = Months[i]
                        validMonth = True
                        j = i+1
                        break
                    elif monthRaw.lower() == MonthsL[i]:
                        validMonth = False
                        print(inputRaw + " - INVALID: Month formatting incorrect capitalization")
                        poorFormatting = True
                        break
                    else:
                        validMonth = False
                        
             

            if validMonth == False and poorFormatting == False:
                print(inputRaw + " - INVALID: month dosent exist ")
                validDate = False
                break

            
            if month != Months[1]:#feburary delt with at end of program    
                if int(dayRaw) > int(DayMax[j-1]):
                    print(inputRaw + " - INVALID: day exceeds months max daycount")
                    validDate = False
                    break
        
            
            yearRaw = input[2]
            if not yearRaw.isnumeric():
                print(inputRaw + " - INVALID: inccorect input type for year")
                validDate = False
                break
            elif len(yearRaw)>4:
                print(inputRaw + " - INVALID: year input too long")
                validDate = False
                break

        except ValueError:
            print(inputRaw + " - INVALID: input required before divider")    
        except IndexError:
            print(inputRaw + " - INVALID: insufficent input")
            validDate = False
            break
        
        try:
            if len(yearRaw) < 2:
                print(inputRaw + " - INVALID input year too small")
                validDate = False
                break
        
        

            year = yearRaw
            if len(yearRaw) == 2:
                if(int(yearRaw) <= 49):
                    year = (str(20) + yearRaw)
                else:
                    year = (str(19) + yearRaw) 

            if  not 1753 <= int(year) <= 3000:
                print(inputRaw + " - INVALID: Year out of range.")
                validDate = False
                break


            #leapyear check
            leap = "is Not Leap Year"    
            if (int(year) % 4 == 0):
                leap = "is Leap Year"
                if(int(year) % 100 == 0):
                    leap = "is Not leap Year"
                    if(int(year) % 400 == 0):
                        leap = "is Leap Year"

        except ValueError:
            print(inputRaw + " - INVALID: UNKNOWN REASON")
            validDate = False 
            break   
        except TypeError:
            print(inputRaw + " - INVALID: year input invalid")
            validDate = False
            break

        if month == "Feb":
            if  int(dayRaw) >= 29 and leap != "is Leap Year":
                print(inputRaw + " - INVALID day for feburary")
                validDate = False
                break

            elif(int(dayRaw) >= 30):
                print(inputRaw + " - INVALID day for feburary")
                validDate = False
                break

        if len(dayRaw) < 2:
            dayRaw = "0"+ dayRaw

        if validDate and validMonth:
            print(dayRaw +" " +month + " " + year)
        validDate = False
        validMonth = False
    #print("please enter the date:")
