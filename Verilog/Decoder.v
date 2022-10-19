module Decoder2To4(
	input enable,
	input[1:0] in,
	output[3:0] out);

	assign out[0] = enable & ~in[1] & ~in[0];
	assign out[1] = enable & ~in[1] & in[0];
	assign out[2] = enable & in[1] & ~in[0];
	assign out[3] = enable & in[1] & in[0];

endmodule

module Decoder2To4Alternative(
	input enable,
	input[1:0] in,
	output[3:0] out);
   
	assign out = enable ? 1 << in : 0; 

endmodule


