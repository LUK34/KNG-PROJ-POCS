#!/bin/bash
source colorCodes.sh
source shellNames.sh


# -----------------------------------------------------------------------------------------------------------------

#Prerequisites
LOG_PATH="/usr/shell_logs"
CURR_YEAR_NUM_VALUE=$(date +"%Y") 	 #eg=2024
CURR_MONTH_NAME_VALUE=$(date +"%B")	 #eg= November
CURR_DATE_VALUE=$(date +"%d-%m-%Y")	 #eg= 18-11-2024
CURR_TIME_VALUE=$(date +"%I_%M_%S_%p") #eg= 9_13_56_AM

# 2024 -> November -> 18-11-2024 -> jerryMouse -> jerryMouse_STATUS_9_13_56_AM.txt

#jerryMouse_STATUS_9_13_56_AM.txt
# -----------------------------------------------------------------------------------------------------------------

# Log content for testing
TEST_LOGS="This is the first log entry.
This is the second log entry.
This is the third log entry."

# -----------------------------------------------------------------------------------------------------------------


# Function responsible for handling logs
# Function START.
log_creator(){

	NAME_PARAM=$1
	
	cd "$LOG_PATH" || error_echo "$LOG_PATH" 
	
	
	# -------------------- YEAR WISE DIRECTORY START -------------------------------------
	# Check if `current system year` directory exists or not
	if [ ! -d "$CURR_YEAR_NUM_VALUE" ] 
	then
		
		# Create the `current system year` directory with read, write and execute permissions
		mkdir -m 777 "$CURR_YEAR_NUM_VALUE"
		highlight_echo "${CURR_YEAR_NUM_VALUE}" "directory"
		echo
	fi
	# -------------------- YEAR WISE DIRECTORY END -------------------------------------
        
	# Move into the `current system year` directory
	cd "$CURR_YEAR_NUM_VALUE" || error_echo "$CURR_YEAR_NUM_VALUE"  
			
	# -------------------- MONTH WISE DIRECTORY START -------------------------------------
	# Check if the `current_system month` directory exists or not
	if [ ! -d "$CURR_MONTH_NAME_VALUE" ]
	then
				
		# Create the `current system month` directory with read, write and execute permissions
		mkdir -m 777 "$CURR_MONTH_NAME_VALUE"
		highlight_echo "${CURR_MONTH_NAME_VALUE}" "directory"
		echo
	fi
	# -------------------- MONTH WISE DIRECTORY END -------------------------------------
		
	# Move into the `current system month` directory
	cd "$CURR_MONTH_NAME_VALUE" || error_echo "$CURR_MONTH_NAME_VALUE"
		
	
	# -------------------- DATE WISE DIRECTORY START -------------------------------------
	# Check if the `current system date` directory exists or not
	if [ ! -d "$CURR_DATE_VALUE" ]
	then
				
		# Create the `current system date` directory with read, write and execute permissions
		mkdir -m 777 "$CURR_DATE_VALUE"
		highlight_echo "${CURR_DATE_VALUE}" "directory"
		echo
	fi
	# -------------------- DATE WISE DIRECTORY END -------------------------------------
		

	# -------------------- CASE CONDITIONS -> START --------------------------
	# Move into the `current system date` directory
	 cd "$CURR_DATE_VALUE" || error_echo "$CURR_DATE_VALUE"
					
				# -------------------- JERRY_MOUSE CASE -> START --------------------------
				case $NAME_PARAM in
					$JERRY_MOUSE )
						# --- JERRY_MOUSE DIRECTORY -> START 
						if [ ! -d "$JERRY_MOUSE" ]
						then
							mkdir -m 777 "$JERRY_MOUSE"
							#echo -e "${COLOR_GREEN_BG} $JERRY_MOUSE directory is CREATED ${EXIT}"
							highlight_echo "${JERRY_MOUSE}" "directory"
							echo
						fi
						# --- JERRY_MOUSE DIRECTORY -> END
						
						#cd "$JERRY_MOUSE" || { echo -e "${COLOR_RED_BG} Error: Cannot access $JERRY_MOUSE ${EXIT}"; exit 1; }
						cd "$JERRY_MOUSE" || error_echo "$JERRY_MOUSE"
						
						# --- JERRY_MOUSE LOG Insertion -> START
						echo "$TEST_LOGS" >> "${JERRY_MOUSE}_status_${CURR_TIME_VALUE}.txt"
						highlight_echo "${JERRY_MOUSE}_status_${CURR_TIME_VALUE}.txt" "file"
						echo
						# --- JERRY_MOUSE LOG Insertion -> END
					;;
				# -------------------- JERRY_MOUSE CASE -> END --------------------------
					"vivek" )
						echo "You don't have access to the folder."
					;;
					* )
						echo "Doesnt satisfy the CASE condition. Tell DEVOPS Engineer to check the code..."
				esac
				

	# -------------------- CASE CONDITIONS -> END --------------------------			
				
}
# Function END.


# Function is called here
log_creator	"jerryMouse"




