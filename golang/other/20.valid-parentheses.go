package other

/*
用栈，遇到左括号入栈，遇到右括号则 stack 出栈，然后看跟右括号是否匹配
最后判断栈是否为空
*/
func isValid(s string) bool {
	stack := make([]string, 0, len(s))
	for _, b := range s {
		p := string(b)
		if p == ")" || p == "]" || p == "}" {
			if len(stack) == 0 {
				return false
			}
			if !match(p, stack[len(stack)-1]) {
				return false
			}
			stack = stack[:len(stack)-1]
		} else {
			stack = append(stack, p)
		}
	}
	return len(stack) == 0
}

func match(a, b string) bool {
	switch a {
	case ")":
		return b == "("
	case "}":
		return b == "{"
	case "]":
		return b == "["
	}
	return false
}
