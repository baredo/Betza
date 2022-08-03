# Betza Parser
The objective of this project is to allow parsing the Betza notation into an easily expressible code in a diagram/drawing view.

# What is Betza?
Betza notation, also referred to as Betza's funny notation, is a compact method to describe the way pieces move in chess-like board games. It is in common use in the world of chess variants. It was originally invented by the American chess master Ralph Betza. It is more used to write down how a piece moves, rather than the actual notation for the piece. This can be a helpful shorthand for very large chess or especially shogi variants with various types of pieces.

# Different versions?
Yes, the Betza notation has gone through different transformations until reaching version 2.0, most are core-compatible extensions but exist a fork between Bex and Betza 2.0 version, personally i think Betza 2.0 version its more complete so it will be the one this program use.

For additional information about the notation and how it works, here its a link with documentation:
https://www.gnu.org/software/xboard/Betza.html
and
https://en.wikipedia.org/wiki/Betza%27s_funny_notation

# Disclaimer
The program is still in early stages of development, but the foundation works, at the moment you can expect only support for the notation of the base version of betza 1.0.

# Installation
No need to install anything, the program work execution a .jar on a command console / terminal

# Use
The program is setup to work on a command console / terminal etc..
Windows user need have install java and have it properly configured.
Execute the program with this command: **java -jar CMDParse *notation to parse***

Example1: java -jar CMDParse W -> Its a chess Rook   
Example2: java -jar CMDParse WWFF -> Its a chess Queen;  etc... 

Then the program draw a board with a piece in it and show the valid moves.
The code of symbols is the next:

P -> Piece 

o -> Step 

\* -> Jump 

\- -> Slide (step with infinite range)

Note that slide is represented by the full range of bars {|/-\\}

Queen Example: WWFF

\\|/<br>-P-<br>/|\

# Roadmap
Betza v1.0 -   90%        Fixing bugs<br>
Betza v2.0 -    0%         Not started yet<br>
CMDParse v1.0 -   100%       Done<br>
UserIntarface -   0%         Not started yet
