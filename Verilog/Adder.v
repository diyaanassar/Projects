module HalfAdder (
	input a, 
	input b,
	output s,
	output c);

	xor g1(s, a, b);
	and g2(c, a, b);

endmodule

module FullAdder (
	input a, 
	input b,
	input Cin,
	output s,
	output Cout);

	wire w1, w2, w3;

 	HalfAdder ha0 (a, b, w1, w2);
	HalfAdder ha1 (w1, Cin, s, w3);
	or g1(Cout, w2, w3);

endmodule

module Adder3BitStructural(
	input [2:0] a,
	input [2:0] b,
	input Cin,
	output [2:0] s,
	output Cout);

	wire [2:1] c;

	FullAdder fa0 (a[0], b[0], Cin, s[0], c[1]);
	FullAdder fa1 (a[1], b[1], c[1], s[1], c[2]);
        FullAdder fa2 (a[2], b[2], c[2], s[2], Cout);

endmodule	

module Adder3BitDataflow(
	input [2:0] a,
	input [2:0] b,
	input Cin,
	output [2:0] s,
	output Cout);

	wire [3:0] result;

	assign result[3:0] = a[2:0] + b[2:0] + Cin;
	assign Cout = result[3];
	assign s[2:0] = result[2:0];
endmodule

module AdderTest;

	wire[2:0] s;
	wire c_out;
	reg[2:0] a, b;

	Adder3BitDataflow adder3bit(a, b, 1'b0, s, c_out);

	initial begin
	$monitor("%d a=%d, b=%d, s=%b, c_out=%b",$time,a,b,s,c_out);

	#10 a = 2;
	    b = 2;

	#10 a = 1;
	    b = 5;

	#10 a = 3;
	    b = 6;

        end
endmodule



