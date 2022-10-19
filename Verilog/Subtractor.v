module HalfAdder (
	input a,
	input b,
	output s,
	output c);

	xor G1(s, a, b);
	and G2(c, a, b);
endmodule

module FullAdder(
	input x,
	input y,
	input z,
	output s,
	output c);

	wire w1, w2, w3;
	
	HalfAdder half_adder1(x, y, w1, w2);
	HalfAdder half_adder2(w1, z, s, w3);
	or G1 (c, w2, w3);

endmodule

module Adder3BitStructural(
	input[2:0] a,
	input[2:0] b,
	input c_in,
	output[2:0] s,
	output c_out);

	wire[2:1] c;

	FullAdder full_adder_0(a[0], b[0], c_in, s[0], c[1]);
	FullAdder full_adder_1(a[1], b[1], c[1], s[1], c[2]);
	FullAdder full_adder_2(a[2], b[2], c[2], s[2], c_out);

endmodule


module Adder3BitDataflow(
	input[2:0] a,
	input[2:0] b,
	input c_in,
	output[2:0] s,
	output c_out);

	wire [3:0] result;

        assign result = a + b + c_in;
        assign c_out = result[3];
        assign s[2:0] = result[2:0];

endmodule

module Subtractor3BitStructural(
	input[2:0] a,
	input[2:0] b,
	output[2:0] d);

        wire c_out;
        wire[2:0] not_b;

	not g1(not_b[0], b[0]);
	not g2(not_b[1], b[1]);
	not g3(not_b[2], b[2]);

	Adder3BitStructural adder_3_bit (a, not_b, 1'b1, d, c_out);

endmodule 


module Subtractor3BitDataflow(
	input[2:0] a,
	input[2:0] b,
	output[2:0] d);

	assign d = a - b; 

endmodule


module Subtractor3BitTest;

	wire[2:0] d;
        reg[2:0] a, b;

	Subtractor3BitStructural subtractor_3_bit(a, b, d);

	initial begin
	$monitor("%d a=%b, b=%b, d=%b ", $time,a,b,d);

	#10 a = 2;
	    b = 2;

	#10 a = 1;
	    b = 5;

	#10 a = 3;
	    b = 6;
	end
endmodule


