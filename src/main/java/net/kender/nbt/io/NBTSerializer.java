package net.kender.nbt.io;

import net.kender.io.Serializer;
import net.kender.nbt.tag.Tag;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class NBTSerializer implements Serializer<NamedTag> {

	private boolean compressed;

	public NBTSerializer() {
		this(true);
	}

	public NBTSerializer(boolean compressed) {
		this.compressed = compressed;
	}

	@Override
	public void toStream(NamedTag object, OutputStream out) throws IOException {
		NBTOutputStream nbtOut;
		if (compressed) {
			nbtOut = new NBTOutputStream(new GZIPOutputStream(out, true));
		} else {
			nbtOut = new NBTOutputStream(out);
		}
		nbtOut.writeTag(object, Tag.DEFAULT_MAX_DEPTH);
		nbtOut.flush();
	}
}
