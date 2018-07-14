#!/bin/sh
echo "Starting application..."

# export gpio

#echo 503 > /sys/class/gpio/export
#echo out > /sys/class/gpio/gpio503/direction


# set up environment

export QT_QPA_FONTDIR=/usr/share/fonts
export FB_MULTI_BUFFER=2
rmmod evbug > /dev/null 2>&1


# run framebuffer demo application
if [ -x /usr/bin/Qt5_CinematicExperience ]; then
	Qt5_CinematicExperience -platform eglfs > /dev/null 2>&1 &
fi

# or alternate run x11 browser application
if [ -x /usr/bin/chromium ]; then
	export DISPLAY=:0
	/usr/bin/chromium --disable-gpu --no-sandbox --disable-session-chrashed-bubble https://www.kernelconcepts.de &
fi
