package checkout.v1.models

import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.{GreaterEqual, Interval}
import eu.timepit.refined._

package object models {
  type Pounds = Int Refined ValidPound
  type Penny  = Int Refined ValidPenny

  type ValidPound = GreaterEqual[W.`0`.T]
  type ValidPenny = Interval.Closed[W.`0`.T, W.`99`.T]
}
