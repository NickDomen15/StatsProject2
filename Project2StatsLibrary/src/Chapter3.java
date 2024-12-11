import java.math.BigInteger;

/**
 * Chapter3 --- methods for formulas from Chapter 3
 * @author Nick Domenico
 */

/*
 *  Possible extra credit: exceptions for all distributions 
 *  and expected/variance/standard deviation methods 
 *  for geometric, hypergeometric, and negative 
 *  binomial distributions
 */
public class Chapter3 {

	// Permutations and Combinations
	
	/**
	 * Returns the factorial of an int input.
	 * @param int input
	 * @return BigInteger
	 *
	 *
	 *  code used from:
	 *  https://javarevisited.blogspot.com/2015/08/how-to-calculate-large-factorials-using-BigInteger-Java-Example.html#axzz8HdeQmnni
	 */
	public BigInteger factorial(int input) {
		
		BigInteger result = new BigInteger("1");
		
		for (int i = input; i > 0; i--) {
			result = result.multiply(BigInteger.valueOf(i));
		}
			
		return result;		
	}
		
		
	/**
	 * Returns the combination of n choose r.
	 * @param int n
	 * @param int r
	 * @return BigInteger
	 */
	public BigInteger combination(int n, int r) {
			
		BigInteger result = factorial(n).divide((factorial(r).multiply(factorial(n-r))));
			
		return result;		
	}
	
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		
	// Hypergeometric Distribution
	
	/**
	 * Returns the hypergeometric
	 * distribution for N, n, r, and y.
	 * @param int N
	 * @param int n
	 * @param int r
	 * @param int y
	 * @return double
	 * @throws InvalidPercentageException - if the 
	 *         resulting percentage is negative or 
	 *         greater than 1
	 */
	public double hypergeometricDistribution(int N, int n, int r, int y) throws InvalidPercentageException {
			
		double result = 0;
			
		result = (combination(r, y).doubleValue() * combination((N - r), (n - y)).doubleValue());
			
		result = result/combination(N, n).doubleValue();
			
		if (result < 0 || result > 1) {
			throw new InvalidPercentageException("\nPercentage cannot be negative or greater than 1");
		}
		else  {
			return result;
		}	
	}
		
		
	/**
	 * Returns the expected value 
	 * of a hypergeometric distribution.
	 * @param int N
	 * @param int n
	 * @param int r
	 * @return double
	 */
	public double hypergeometricExpected(int N, int n, int r) {
			
		double result = (n * r) / N;
				
		return result;
	}
		
		
	/**
	 * Returns the variance of 
	 * a hypergeometric distribution.
	 * @param int N
	 * @param int n
	 * @param int r
	 * @return double
	 */
	public double hypergeometricVariance(int N, int n, int r) {
			
		double result = n * combination(r, N).doubleValue() * combination((N - r), N).doubleValue() * combination((N - n), (N - 1)).doubleValue();
			
		return result;
	}
		
		
	/**
	 * Returns the standard deviation 
	 * of a hypergeometric distribution.
	 * @param int N
	 * @param int n
	 * @param int r
	 * @return double
	 */
	public double hypergeometricStandardDeviation(int N, int n, int r) {
			
		double result = Math.sqrt(hypergeometricVariance(N, n, r));
			
		return result;
	}
		
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	// Negative Binomial Distribution
		
	/**
	 * Returns the negative binomial 
	 * distribution for p, y, and r.
	 * @param double p
	 * @param int y
	 * @param int r
	 * @return double
	 * @throws InvalidPercentageException - if the 
	 *         resulting percentage is negative or 
	 *         greater than 1
	 */
	public double negativeBinomialDistribution(double p, int y, int r) throws InvalidPercentageException {
			
		double q = (1 - p);
		double result = 0;
			
		result = (combination((y - 1), (r - 1)).doubleValue() * Math.pow(p, r) * Math.pow(q, (y - r)));
			
		if (result < 0 || result > 1) {
			throw new InvalidPercentageException("\nPercentage cannot be negative or greater than 1");
		}
		else  {
			return result;
		}
	}
		
		
	/**
	 * Returns the expected value of 
	 * a negative binomial distribution.
	 * @param double p
	 * @param int r
	 * @return double
	 */
	public double negativeBinomialExpected(double p, int r) {
			
		double result = r / p;
			
		return result;
	}
		
		
	/**
	 * Returns the variance of a 
	 * negative binomial distribution.
	 * @param double p
	 * @param int r
	 * @return double
	 */
	public double negativeBinomialVariance(double p, int r) {
			
		double result = (r * (1 - p)) / Math.pow(p, 2);
			
		return result;
	}
		

	/**
	 * Returns the standard deviation 
	 * of a negative binomial distribution.
	 * @param p
	 * @param r
	 * @return
	 */
	public double negativeBinomialStandardDeviation(double p, int r) {
			
		double result = Math.sqrt(negativeBinomialVariance(p, r));
			
		return result;
	}
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	// Poisson Distribution
		
	/**
	 * Returns the Poisson Distribution 
	 * P(Y=y) for lambda and y.
	 * @param double lambda
	 * @param int y
	 * @return double
	 * @throws InvalidPercentageException - if the 
	 *         resulting percentage is negative or 
	 *         greater than 1
	 */
	public double poissonDistribution(double lambda, int y) throws InvalidPercentageException {
			
		double result = 0;
			
		result = Math.pow(lambda, y) / factorial(y).doubleValue() * Math.pow(Math.E, -lambda);
			
		if (result < 0 || result > 1) {
			throw new InvalidPercentageException("\nPercentage cannot be negative or greater than 1");
		}
		else  {
			return result;
		}
	}
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	// Tchebysheff's Theorem
		
	/**
	 * Returns the result of Tchebysheff's Theorem 
	 * for mean, standardDeviation, and upperBound.
	 * @param double mean
	 * @param double standardDeviation
	 * @param double upperBound
	 * @return double
	 * @throws InvalidPercentageException - if the 
	 *         resulting percentage is negative or 
	 *         greater than 1
	 */
	public double tchebysheffsTheorem(double mean, double standardDeviation, double upperBound) throws InvalidPercentageException {
			
		double result = 0;
		double k = 0;
		double withinNumber = upperBound - mean;
		k = withinNumber / standardDeviation;
			
		result = 1 - (1/Math.pow(k, 2));
			
		if (result < 0 || result > 1) {
			throw new InvalidPercentageException("\nPercentage cannot be negative or greater than 1");
		}
		else  {
			return result;
		}
	}

}
	

