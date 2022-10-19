module ComparatorDataflow1(
	input [3:0] a,
	input [3:0] b,
	output eq,
	output lt,
	output gt);

	wire [3:0] x;
	
	assign x = a ~^ b;

	assign eq = x[3] & x[2] & x[1] & x[0];
	assign lt = (~a[3] & b[3]) |
		    (x[3] & ~a[2] & b[2]) |
		    (x[3] & x[2] & ~a[1] & b[1]) |
		    (x[3] & x[2] & x[1] & ~a[0] & b[0]);
	assign gt = (a[3] & ~b[3]) |
		    (x[3] & a[2] & ~b[2]) |
		    (x[3] & x[2] & a[1] & ~b[1]) |
		    (x[3] & x[2] & x[1] & a[0] & ~b[0]);
endmodule


module ComparatorDataflow2(
	input [3:0] a,
	input [3:0] b,
	output eq,
	output lt,
	output gt);

	assign eq = a == b;
	assign lt = a < b;
	assign gt = a > b;

endmodule

module ComparatorTest;

	reg [3:0] a, b;
	wire eq, lt, gt;

	ComparatorDataflow1 cmp (a, b, eq, lt, gt);

	initial begin
	
	$monitor("%d a=%d, b=%d, eq=%b, lt=%b, gt=%b", $time, a, b, eq, lt, gt);
	
	#10 a = 4'b0011; 
	    b = 4'b1100;  

	#10 a = 4'b1010; 
	    b = 4'b0100;  

	#10 a = 4'b0011; 
	    b = 4'b0011; 

	#10 $finish;
	end
endmodule

