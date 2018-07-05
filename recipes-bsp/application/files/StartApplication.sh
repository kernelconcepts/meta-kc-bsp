#!/bin/sh
echo "Starting application..."

# export gpio

#echo 503 > /sys/class/gpio/export
#echo out > /sys/class/gpio/gpio503/direction


# set up environment

export QT_QPA_FONTDIR=/usr/share/fonts/truetype
export FB_MULTI_BUFFER=2
rmmod evbug 2>&1 > /dev/null


# run application
if [ -x /usr/bin/Qt5_CinematicExperience ]; then
	Qt5_CinematicExperience -platform eglfs&
fi

