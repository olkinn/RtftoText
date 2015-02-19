My first pretty serious program on java which translates the contents of a RTF file to a TXT file.

A RTF file consists of unformatted text, control words, control symbols, and groups. A "control word" is a specially formatted command that RTF uses to mark printer control codes and information that applications use to manage documents. A control word takes the following form:

<LetterSequence>[]

A "group" consists of text and control words or control symbols enclosed in braces ({ }). The opening brace indicates the start of the group and the closing brace indicates the end of the group. Each group specifies the text affected by the group and the different attributes of that text. The RTF file can also include groups for fonts, styles, screen color, pictures, footnotes, annotations, headers and footers, summary information, fields, and bookmarks, as well as document-, section-, paragraph-, and character-formatting properties.

My program has an array of control words that can't be used with normal text, therefore we can ignore everything inside the groups of these control words. And the normal text is saved at the end of control word sequences, right before a group ends. Using these 2 ideas, we can pretty much pull the normal text out of a rtf file.
