module CircuitStructural(
	input a,
	input b,
	input c,
	output d,
	output e);

	wire w1;

	and g1(w1, a, b);
	not g2(e, c);
	or g3(d, w1, e);

endmodule

module CircuitDataflow(
	input a,
	input b,
	input c,
	output d,
	output e);

	assign d = (a & b) | ~c;
	assign e = ~c;

endmodule

module CircuitBehavioral(
	input a,
	input b,
	input c,
	output d,
	output e);
 
	reg d, e;
	always@(a, b, c)
	   case({a,b,c})
		0: {d, e} = 2'b11;
		1: {d, e} = 2'b00;
		2: {d, e} = 2'b11;
		3: {d, e} = 2'b00;
		4: {d, e} = 2'b11;
		5: {d, e} = 2'b00;
		6: {d, e} = 2'b11;
		7: {d, e} = 2'b10;
            endcase
endmodule

module CircuitTest;

	wire d, e;
	reg a, b, c;

        CircuitBehavioral circuit1(a, b, c, d, e);

	initial begin
	
	$monitor("%d abc=%b%b%b de=%b%b", $time, a, b, c, d, e);

	#10 {a, b, c} = 0;
	#10 {a, b, c} = 1;
	#10 {a, b, c} = 2;
	#10 {a, b, c} = 3;
	#10 {a, b, c} = 4;
	#10 {a, b, c} = 5;
	#10 {a, b, c} = 6;
	#10 {a, b, c} = 7;
	#10 $finish;

	end
endmodule
