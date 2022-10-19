module Mux4to1Structural (
	input enable,
	input [3:0] in,
	input [1:0] selector,
	output out);

	wire[1:0] not_selector;
	wire [3:0] w;

	not g1(not_selector[0], selector[0]);
	not g2(not_selector[1], selector[1]);

	and g3(w[0], enable, in[0], not_selector[1], not_selector[0]);
	and g4(w[1], enable, in[1], not_selector[1], selector[0]);
	and g5(w[2], enable, in[2], selector[1], not_selector[0]);
	and g6(w[3], enable, in[3], selector[1], selector[0]);

	or(out, w[0], w[1], w[2], w[3]);

endmodule

module Mux4to1Dataflow(
	input enable,
	input [3:0] in,
	input [1:0] selector,
	output out);

	assign out = enable ? in[selector] : 0;

endmodule

module Mux4to1Behavioral(	
	input enable,
	input [3:0] in,
	input [1:0] selector,
	output out);

	reg out;
	
	always @ (enable, in, selector)
	    if (enable)
 		out = in[selector];
	    else
	        out = 0;
endmodule 

module Mux3Bit4to1(
	input enable,
	input [2:0] in0,
	input [2:0] in1,
	input [2:0] in2,
	input [2:0] in3,
	input [1:0] selector,
	output [2:0] out);

	reg [2:0] out;

	always@(enable, in0, in1, in2, in3, selector)
	     if (enable)
		case (selector)
		    0: out = in0;
		    1: out = in1;
		    2: out = in2;
		    3: out = in3;
                endcase
             else
	         out = 0;

endmodule

module Mux3Bit4to1Test;

	reg enable;	
	reg [2:0] in0, in1, in2, in3;
	reg [1:0] selector;
	wire [2:0] out;

	Mux3Bit4to1 mux2(enable, in0, in1, in2, in3, selector, out);
	initial begin
	$monitor("%d enable=%b in=(%d,%d,%d,%d) selector=%d, out=%d",
	          $time, enable, in0, in1, in2, in3, selector, out);

	enable = 1;
	in0 = 4;
	in1 = 5;
	in2 = 6;
	in3 = 7;
	selector = 0;

	#10 selector = 1;
	#10 selector = 2;
	#10 selector = 3;
	#10 $finish;
	end
endmodule

/*
module testmux4to1;
	
	reg enable; 
        reg [3:0] in; 
	reg [1:0] selector;
	wire out;

	Mux4to1Behavioral m1(enable, in, selector, out);
	
	initial begin
	$monitor("%d enable=%b, in=%b selector=%b, out=%b", $time, enable, in, selector, out);

	enable = 1;
	in[0] = 0;
	in[1] = 1;
	in[2] = 0;
	in[3] = 1;
	selector = 0;

	#10 selector = 1;	
	#10 selector = 2;	
	#10 selector = 3;	
	#10 $finish;
    	
	end

endmodule*/
