package ar.com.hjg.pngj;

import java.io.OutputStream;

import ar.com.hjg.pngj.chunks.ChunkHelper;
import ar.com.hjg.pngj.chunks.ChunkRaw;

/**
 * outputs the stream for IDAT chunk , fragmented at fixed size (64k default).
 */
public class PngIDatChunkOutputStream extends ProgressiveOutputStream {
	private static final int SIZE_DEFAULT = 65556; // 64k rather arbitrary
	private final OutputStream outputStream;

	PngIDatChunkOutputStream(OutputStream outputStream) {
		this(outputStream, 0);
	}

	PngIDatChunkOutputStream(OutputStream outputStream, int size) {
		super(size > 0 ? size : SIZE_DEFAULT);
		this.outputStream = outputStream;
	}

	@Override
	protected final void flushBuffer(byte[] b, int len) {
		ChunkRaw c = new ChunkRaw(len, ChunkHelper.b_IDAT, false);
		c.data = b;
		c.writeChunk(outputStream);
	}
}
