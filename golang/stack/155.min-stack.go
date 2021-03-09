package stack

/*
用一个辅助栈，记录 push 一个元素 x 进去时，当前栈的最小值 y，当 pop 时 x 和 y 一起出
*/
type MinStack struct {
	Stack []int
	Min   []int
}

/** initialize your data structure here. */
func Constructor() MinStack {
	return MinStack{
		Stack: []int{},
		Min:   []int{},
	}
}

func (this *MinStack) Push(x int) {
	this.Stack = append(this.Stack, x)
	if len(this.Min) == 0 {
		this.Min = append(this.Min, x)
	} else {
		this.Min = append(this.Min, min(x, this.Min[len(this.Min)-1]))
	}
}

func (this *MinStack) Pop() {
	this.Stack = this.Stack[:len(this.Stack)-1]
	this.Min = this.Min[:len(this.Min)-1]
}

func (this *MinStack) Top() int {
	return this.Stack[len(this.Stack)-1]
}

func (this *MinStack) GetMin() int {
	return this.Min[len(this.Min)-1]
}

func min(a, b int) int {
	if a > b {
		return b
	}
	return a
}
