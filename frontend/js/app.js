document.addEventListener('DOMContentLoaded', () => {
  const solveBtn = document.getElementById('solveBtn');
  const chartCtx = document.getElementById('chart').getContext('2d');
  const solutionTable = document.getElementById('solutionTable');
  let chart = null;

  solveBtn.addEventListener('click', async () => {
    try {
      // Get input values
      const equations = [
        document.getElementById('eq1').value,
        document.getElementById('eq2').value
      ];
      
      const initialValues = [
        parseFloat(document.getElementById('y10').value),
        parseFloat(document.getElementById('y20').value)
      ];
      
      const t0 = parseFloat(document.getElementById('t0').value);
      const tEnd = parseFloat(document.getElementById('tEnd').value);
      const step = parseFloat(document.getElementById('step').value);
      const method = document.getElementById('method').value;

      // Prepare request
      const request = {
        equations: equations,
        initialValues: initialValues,
        t0: t0,
        tEnd: tEnd,
        step: step,
        solverType: method
      };

      // Send request to backend
      const response = await fetch('http://localhost:8080/api/ode/solve', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(request)
      });

      if (!response.ok) {
        throw new Error(await response.text());
      }

      const data = await response.json();
      displayResults(data);
    } catch (error) {
      console.error('Error:', error);
      alert('Error: ' + error.message);
    }
  });

  function displayResults(data) {
    // Update chart
    updateChart(data);
    
    // Update table
    updateTable(data);
  }

  function updateChart(data) {
    if (chart) {
      chart.destroy();
    }
    
    const datasets = [];
    for (let i = 0; i < data.yValues[0].length; i++) {
      datasets.push({
        label: `y${i+1}(t)`,
        data: data.yValues.map(y => y[i]),
        borderColor: getColor(i),
        backgroundColor: 'rgba(0, 0, 0, 0)',
        tension: 0.1
      });
    }
    
    chart = new Chart(chartCtx, {
      type: 'line',
      data: {
        labels: data.tValues.map(t => t.toFixed(2)),
        datasets: datasets
      },
      options: {
        responsive: true,
        scales: {
          x: {
            title: {
              display: true,
              text: 't'
            }
          },
          y: {
            title: {
              display: true,
              text: 'y(t)'
            }
          }
        },
        plugins: {
          title: {
            display: true,
            text: data.methodName
          }
        }
      }
    });
  }

  function updateTable(data) {
    let tableHTML = `
      <table>
        <thead>
          <tr>
            <th>t</th>
            ${data.yValues[0].map((_, i) => `<th>y${i+1}(t)</th>`).join('')}
          </tr>
        </thead>
        <tbody>
    `;
    
    for (let i = 0; i < data.tValues.length; i++) {
      if (i % 5 === 0) { // Show every 5th point for readability
        tableHTML += `
          <tr>
            <td>${data.tValues[i].toFixed(4)}</td>
            ${data.yValues[i].map(y => `<td>${y.toFixed(6)}</td>`).join('')}
          </tr>
        `;
      }
    }
    
    tableHTML += `
        </tbody>
      </table>
    `;
    
    solutionTable.innerHTML = tableHTML;
  }

  function getColor(index) {
    const colors = [
      'rgb(75, 192, 192)',
      'rgb(255, 99, 132)',
      'rgb(54, 162, 235)',
      'rgb(255, 159, 64)',
      'rgb(153, 102, 255)'
    ];
    return colors[index % colors.length];
  }
});