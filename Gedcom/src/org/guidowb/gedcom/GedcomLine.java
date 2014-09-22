package org.guidowb.gedcom;

import java.io.BufferedReader;
import java.io.IOException;

class GedcomLine {

	private String line;
	
	public boolean read(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		return (line != null);
	}

	public GedcomRecord parse(Gedcom gedcom, int lineno) {
		int level = Integer.valueOf(getWord());
		String id = getIdentifier();
		String tag = getWord();
		String xref = getIdentifier();
		String value = getRemainder();
		return new GedcomRecord(gedcom, lineno, level, id, tag, xref, value);
	}

	private String getWord() {
		int offset = line.indexOf(' ');
		if (offset < 0) return line;
		String word = line.substring(0, offset);
		line = line.substring(offset + 1);
		return word;
	}
	
	private String getIdentifier() {
		if (!line.startsWith("@")) return null;
		return getWord().replaceAll("^@", "").replaceAll("@$", "");
	}

	private String getRemainder() {
		return line;
	}
}
