#!/bin/sh
#

DIR="/#SYSCONFDIR#/firststart.d"
DIR_OVERLAY_LOWER="/media/rfs/ro"

remove_startup_link () {
	if [ -n "`which update-rc.d`" ]; then
		update-rc.d -f firststart remove
	fi
}

if ! [ -d $DIR ]; then
	remove_startup_link
	exit 0
fi

# check if we have a ro overlay or if we can write changes directly
if [ -e "$DIR_OVERLAY_LOWER" ]; then
# using meta-readonly-rootfs-overlay
	echo Mounting rootfs lower rw
	mount $DIR_OVERLAY_LOWER -o remount,rw

	echo Running write tasks...
	run-parts $DIR

	rm -r $DIR_OVERLAY_LOWER/$DIR
	rm $DIR_OVERLAY_LOWER/#SYSCONFDIR#/rc*.d/S*firststart
	rm $DIR_OVERLAY_LOWER/#SYSCONFDIR#/init.d/firststart
	sync
	echo Mounting rootfs lower ro
	mount $DIR_OVERLAY_LOWER -o remount,ro > /dev/null 2>&1
else 
	run-parts $DIR

	rm -rf $DIR
	remove_startup_link
fi

