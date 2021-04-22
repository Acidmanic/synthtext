
BASE=target/synth

DEST=~/.synth

rm -rf $DEST

mkdir -p $DEST

EXEC=$DEST/run.sh

BIN=/usr/local/bin/synth

TEMP=target/temp

echo "$EXEC \$@" > $TEMP

chmod +x $TEMP

cp $BASE/* $DEST		&& \
				\
sudo rm -f $BIN			&& \
				\
sudo mv $TEMP $BIN
