package other

func ReverseWords(s string) string {
	res := make([]byte, 0, len(s))
	begin := 0
	length := len(s)

	for i, b := range s {
		if i == length-1 {
			for j := i; j >= begin; j-- {
				res = append(res, s[j])
			}
		}
		if string(b) == " " {
			for j := i - 1; j >= begin; j-- {
				res = append(res, s[j])
			}
			res = append(res, ' ')
			begin = i + 1
		}
	}
	return string(res)
}
